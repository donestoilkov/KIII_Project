package mk.finki.ukim.mk.lab.web.servlet;

import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.impl.BalloonServiceImpl;
import org.springframework.context.annotation.Profile;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Profile("Done")
@WebServlet (name = "BalloonList", urlPatterns = "")
public class BalloonListServlet extends HttpServlet {
    private final BalloonService balloonService;
    private final SpringTemplateEngine springTemplateEngine;

    public BalloonListServlet(BalloonService balloonService, SpringTemplateEngine springTemplateEngine) {
        this.balloonService = balloonService;
        this.springTemplateEngine = springTemplateEngine;
        Integer requestCount = 0;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req,resp,req.getServletContext());
        context.setVariable("balloons",balloonService.listAll());
        Integer requestCount = 0;
        req.getSession().setAttribute("requests", requestCount);
        springTemplateEngine.process("listBalloons.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String chosenColor = req.getParameter("color");
        req.getSession().setAttribute("color", chosenColor);
        resp.sendRedirect("/selectBalloon");
    }
}
