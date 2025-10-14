package edu.xanderson.PatoCritico.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.util.Map;

@Service
public class EmailTemplateService {

    @Autowired
    private TemplateEngine templateEngine;

    public String processarTemplate(String nomeTemplate, Map<String, Object> variaveis) {
        Context context = new Context();
        context.setVariables(variaveis);
        return templateEngine.process("email/" + nomeTemplate, context);
    }
}