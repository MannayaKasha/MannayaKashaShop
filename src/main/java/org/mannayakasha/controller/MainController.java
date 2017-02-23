package org.mannayakasha.controller;

import org.mannayakasha.entity.Product;
import org.mannayakasha.model.CartInfo;
import org.mannayakasha.model.CustomerInfo;
import org.mannayakasha.model.PaginationResult;
import org.mannayakasha.model.ProductInfo;
import org.mannayakasha.service.OrderService;
import org.mannayakasha.service.ProductService;
import org.mannayakasha.service.Service;
import org.mannayakasha.service.ServiceFactory;
import org.mannayakasha.util.Utils;
import org.mannayakasha.validator.CustomerInfoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 *
 * @author Pavel
 * @version 1.0
 */

@Controller
// Enable Hibernate Transaction.
@Transactional
// Need to use RedirectAttributes
@EnableWebMvc
public class MainController {

    @Resource(name = "productService")
    private ProductService productService;

    @Autowired(required = true)
    @Qualifier(value = "productService")
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Resource(name = "orderService")
    private OrderService orderService;

    @Autowired(required = true)
    @Qualifier(value = "orderService")
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }




    /*@Resource(name = "serviceFactory")
    private ServiceFactory serviceFactory;

    @Autowired(required = true)
    @Qualifier(value = "serviceFactory")
    public void setServiceFactory(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }*/


    @Autowired
    private CustomerInfoValidator customerInfoValidator;

    @InitBinder
    public void myInitBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);

        // For Cart Form.
        // (@ModelAttribute("cartForm") @Validated CartInfo cartForm)
        if (target.getClass() == CartInfo.class) {

        }
        // For Customer Form.
        // (@ModelAttribute("customerForm") @Validated CustomerInfo
        // customerForm)
        else if (target.getClass() == CustomerInfo.class) {
            dataBinder.setValidator(customerInfoValidator);
        }

    }

    /*@RequestMapping(value="/testlocale", method = RequestMethod.GET)
    public ModelAndView user(Locale locale){
        return new ModelAndView("testlocale");
    }*/

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/testlocale", method = RequestMethod.GET)
    public String index(Locale locale, Model model){

        // add parametrized message from controller
        String welcome = messageSource.getMessage("user.title", new Object[]{"John Doe"}, locale);
        model.addAttribute("message", welcome);

        // obtain locale from LocaleContextHolder
        Locale currentLocale = LocaleContextHolder.getLocale();
        model.addAttribute("locale", currentLocale);

        model.addAttribute("startMeeting", "10:30");

        return "testlocale";
    }

    //@RequestMapping(value = "/", method = RequestMethod.GET)
    @RequestMapping(value = "/")
    public String home(HttpServletRequest request, Model model,
                       @RequestParam(value = "name", defaultValue = "") String likeName,
                       @RequestParam(value = "category", defaultValue = "") String likeCategory,
                       @RequestParam(value = "page", defaultValue = "1") int page) {

        final int maxResult = 8;
        final int maxNavigationPage = 10;

        if (likeName.isEmpty() && likeCategory.isEmpty()) {
            //Service service = serviceFactory.getService("productService");
            PaginationResult<ProductInfo> result = productService.queryProducts(page,
                    maxResult, maxNavigationPage);

            model.addAttribute("paginationProducts", result);
        }
        if (likeName.isEmpty() == false) {
            PaginationResult<ProductInfo> result = productService.queryProducts(page,
                    maxResult, maxNavigationPage, likeName);

            model.addAttribute("paginationProducts", result);
        }
        if (likeCategory.isEmpty() == false) {
            PaginationResult<ProductInfo> result = productService.queryProductsByCategory(page,
                    maxResult, maxNavigationPage, likeCategory);

            model.addAttribute("paginationProducts", result);
        }

        CartInfo myCart = Utils.getCartInSession(request);

        model.addAttribute("cartForm", myCart);
        return "index";
    }

    @RequestMapping("/403")
    public String accessDenied() {
        return "errors/403";
    }

    @RequestMapping("/404")
    public String pageNotFound() {
        return "errors/404";
    }

    @RequestMapping({"/buyProduct"})
    public String listProductHandler(HttpServletRequest request, Model model,
                                     @RequestParam(value = "code", defaultValue = "") String code) {

        Product product = null;
        if (code != null && code.length() > 0) {
            product = productService.findProduct(code);
        }
        if (product != null) {

            // Cart info stored in Session.
            CartInfo cartInfo = Utils.getCartInSession(request);

            ProductInfo productInfo = new ProductInfo(product);

            cartInfo.addProduct(productInfo, 1);
        }
        // Redirect to shoppingCart page.
        return "redirect:/shoppingCart";
    }

    @RequestMapping({"/shoppingCartRemoveProduct"})
    public String removeProductHandler(HttpServletRequest request, Model model,
                                       @RequestParam(value = "code", defaultValue = "") String code) {
        Product product = null;
        if (code != null && code.length() > 0) {
            product = productService.findProduct(code);
        }
        if (product != null) {

            // Cart Info stored in Session.
            CartInfo cartInfo = Utils.getCartInSession(request);

            ProductInfo productInfo = new ProductInfo(product);

            cartInfo.removeProduct(productInfo);

        }
        // Redirect to shoppingCart page.
        return "redirect:/shoppingCart";
    }

    // POST: Update quantity of products in cart.
    @RequestMapping(value = {"/shoppingCart"}, method = RequestMethod.POST)
    public String shoppingCartUpdateQty(HttpServletRequest request,
                                        Model model, //
                                        @ModelAttribute("cartForm") CartInfo cartForm) {

        CartInfo cartInfo = Utils.getCartInSession(request);
        cartInfo.updateQuantity(cartForm);

        // Redirect to shoppingCart page.
        return "redirect:/shoppingCart";
    }

    // GET: Show Cart
    @RequestMapping(value = {"/shoppingCart"}, method = RequestMethod.GET)
    public String shoppingCartHandler(HttpServletRequest request, Model model) {
        CartInfo myCart = Utils.getCartInSession(request);

        model.addAttribute("cartForm", myCart);
        return "shoppingCart/shoppingCart";
    }

    // GET: Enter customer information.
    @RequestMapping(value = {"/shoppingCartCustomer"}, method = RequestMethod.GET)
    public String shoppingCartCustomerForm(HttpServletRequest request, Model model) {

        CartInfo cartInfo = Utils.getCartInSession(request);

        // Cart is empty.
        if (cartInfo.isEmpty()) {

            // Redirect to shoppingCart page.
            return "redirect:/shoppingCart";
        }

        CustomerInfo customerInfo = cartInfo.getCustomerInfo();
        if (customerInfo == null) {
            customerInfo = new CustomerInfo();
        }

        model.addAttribute("customerForm", customerInfo);

        return "shoppingCart/shoppingCartCustomer";
    }

    // POST: Save customer information.
    @RequestMapping(value = {"/shoppingCartCustomer"}, method = RequestMethod.POST)
    public String shoppingCartCustomerSave(HttpServletRequest request,
                                           Model model,
                                           @ModelAttribute("customerForm") @Validated CustomerInfo customerForm,
                                           BindingResult result,
                                           final RedirectAttributes redirectAttributes) {

        // If has Errors.
        if (result.hasErrors()) {
            customerForm.setValid(false);
            // Forward to reenter customer info.
            return "shoppingCart/shoppingCartCustomer";
        }

        customerForm.setValid(true);
        CartInfo cartInfo = Utils.getCartInSession(request);

        cartInfo.setCustomerInfo(customerForm);

        // Redirect to Confirmation page.
        return "redirect:/shoppingCartConfirmation";
    }

    // GET: Review Cart to confirm.
    @RequestMapping(value = {"/shoppingCartConfirmation"}, method = RequestMethod.GET)
    public String shoppingCartConfirmationReview(HttpServletRequest request, Model model) {
        CartInfo cartInfo = Utils.getCartInSession(request);

        // Cart have no products.
        if (cartInfo.isEmpty()) {
            // Redirect to shoppingCart page.
            return "redirect:/shoppingCart";
        } else if (!cartInfo.isValidCustomer()) {
            // Enter customer info.
            return "redirect:/shoppingCartCustomer";
        }

        return "shoppingCart/shoppingCartConfirmation";
    }

    // POST: Send Cart (Save).
    @RequestMapping(value = {"/shoppingCartConfirmation"}, method = RequestMethod.POST)
    // Avoid UnexpectedRollbackException (See more explanations)
    @Transactional(propagation = Propagation.NEVER)
    public String shoppingCartConfirmationSave(HttpServletRequest request, Model model) {
        CartInfo cartInfo = Utils.getCartInSession(request);

        // Cart have no products.
        if (cartInfo.isEmpty()) {
            // Redirect to shoppingCart page.
            return "redirect:/shoppingCart";
        } else if (!cartInfo.isValidCustomer()) {
            // Enter customer info.
            return "redirect:/shoppingCartCustomer";
        }
        try {
            orderService.saveOrder(cartInfo);
        } catch (Exception e) {
            // Need: Propagation.NEVER?
            return "shoppingCart/shoppingCartConfirmation";
        }
        // Remove Cart In Session.
        Utils.removeCartInSession(request);

        // Store Last ordered cart to Session.
        Utils.storeLastOrderedCartInSession(request, cartInfo);

        // Redirect to successful page.
        return "redirect:/shoppingCartFinalize";
    }

    @RequestMapping(value = {"/shoppingCartFinalize"}, method = RequestMethod.GET)
    public String shoppingCartFinalize(HttpServletRequest request, Model model) {

        CartInfo lastOrderedCart = Utils.getLastOrderedCartInSession(request);

        if (lastOrderedCart == null) {
            return "redirect:/shoppingCart";
        }

        return "shoppingCart/shoppingCartFinalize";
    }

    @RequestMapping(value = {"/productImage"}, method = RequestMethod.GET)
    public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,
                             @RequestParam("code") String code) throws IOException {
        Product product = null;
        if (code != null) {
            product = this.productService.findProduct(code);
        }
        if (product != null && product.getImage() != null) {
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            response.getOutputStream().write(product.getImage());
        }
        response.getOutputStream().close();
    }
}
