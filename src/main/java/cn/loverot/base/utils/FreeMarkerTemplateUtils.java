package cn.loverot.base.utils;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class FreeMarkerTemplateUtils {
    private final static String Template_Dir = "templates";
    private final static Configuration config = new Configuration(Configuration.VERSION_2_3_28);

    static{
        URL url = FreeMarkerTemplateUtils.class.getClassLoader().getResource(Template_Dir);
        File dir  = new File(url.getFile());
        try {
            config.setDirectoryForTemplateLoading(dir);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        config.setDefaultEncoding("UTF-8");
        config.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_28));
        config.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);

    }

    public static Template getTemplate(String templateName){
        try {
            return config.getTemplate(templateName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String process(Template template,Object model){
        try {
            return org.springframework.ui.freemarker.FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String process(String templateName,Object model){
        return process(getTemplate(templateName),model);
    }
}
