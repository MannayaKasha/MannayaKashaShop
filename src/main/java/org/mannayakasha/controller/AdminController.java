package org.mannayakasha.controller;

import org.mannayakasha.model.*;
import org.mannayakasha.service.OrderService;
import org.mannayakasha.service.ProductService;
import org.mannayakasha.util.Utils;
import org.mannayakasha.validator.ProductInfoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
public class AdminController {

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

    @Autowired
    private ProductInfoValidator productInfoValidator;

    // Configurated In ApplicationContextConfig.
    @Autowired
    private ResourceBundleMessageSource messageSource;

    @InitBinder
    public void myInitBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);

        if (target.getClass() == ProductInfo.class) {
            dataBinder.setValidator(productInfoValidator);
            // For upload Image.
            dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
        }
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(Model model) {

        return "login";
    }

    @RequestMapping(value = {"/accountInfo"}, method = RequestMethod.GET)
    public String accountInfo(Model model, HttpServletRequest request) {

        CartInfo myCart = Utils.getCartInSession(request);

        model.addAttribute("cartForm", myCart);

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getPassword());
        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.isEnabled());

        model.addAttribute("userDetails", userDetails);
        return "account/accountInfo";
    }

    @RequestMapping(value = {"/orderList"}, method = RequestMethod.GET)
    public String orderList(Model model,
                            @RequestParam(value = "page", defaultValue = "1") String pageStr) {
        int page = 1;
        try {
            page = Integer.parseInt(pageStr);
        } catch (Exception e) {
        }
        final int MAX_RESULT = 3;
        final int MAX_NAVIGATION_PAGE = 10;

        PaginationResult<OrderInfo> paginationResult
                = orderService.listOrderInfo(page, MAX_RESULT, MAX_NAVIGATION_PAGE);

        model.addAttribute("paginationResult", paginationResult);
        return "orderList";
    }

    // GET: Show product.
    @RequestMapping(value = {"/product"}, method = RequestMethod.GET)
    public String product(Model model, @RequestParam(value = "code", defaultValue = "") String code) {
        ProductInfo productInfo = null;

        if (code != null && code.length() > 0) {
            productInfo = productService.findProductInfo(code);
        }
        if (productInfo == null) {
            productInfo = new ProductInfo();
            productInfo.setNewProduct(true);
        }
        model.addAttribute("productForm", productInfo);
        return "product";
    }

    // POST: Save product
    @RequestMapping(value = {"/product"}, method = RequestMethod.POST)
    // Avoid UnexpectedRollbackException
    @Transactional(propagation = Propagation.NEVER)
    public String productSave(Model model,
                              @ModelAttribute("productForm") @Validated ProductInfo productInfo,
                              BindingResult result,
                              final RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "product";
        }
        try {
            productService.save(productInfo);
        } catch (Exception e) {
            String message = e.getMessage();
            model.addAttribute("message", message);
            return "product";

        }
        return "redirect:/";
    }

    @RequestMapping(value = {"/order"}, method = RequestMethod.GET)
    public String orderView(Model model, @RequestParam("orderId") String orderId) {
        OrderInfo orderInfo = null;
        if (orderId != null) {
            orderInfo = this.orderService.getOrderInfo(orderId);
        }
        if (orderInfo == null) {
            return "redirect:/orderList";
        }
        List<OrderDetailInfo> details = this.orderService.listOrderDetailInfos(orderId);
        orderInfo.setDetails(details);

        model.addAttribute("orderInfo", orderInfo);

        return "order";
    }
}
