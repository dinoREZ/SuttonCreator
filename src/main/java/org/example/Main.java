package org.example;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.FileUtils;
import org.example.dataModels.DataModel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.example.Config.*;

public class Main {

    public static void main(String[] args) throws IOException, TemplateException {

        List<DataModel> dataModels = getDataModels();

        generateOutput(dataModels);
    }

    private static void generateOutput(List<DataModel> dataModels) throws IOException, TemplateException {
        Configuration freemarkerConfig = new Configuration(Configuration.VERSION_2_3_31);
        freemarkerConfig.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
        freemarkerConfig.setDefaultEncoding("UTF-8");
        freemarkerConfig.setLogTemplateExceptions(false);

        String basePackagePath = createBasePackageDirectory();
        FileUtils.cleanDirectory(new File(basePackagePath));

        for (DataModel dataModel : dataModels) {
            String basePath = dataModel.getPath();
            String templatePath = basePath + "/" + dataModel.getTemplateName();
            String outputDirectory = basePackagePath + basePath;
            String fullOutputPath = outputDirectory + "/" + dataModel.getOutputName();

            Template template = freemarkerConfig.getTemplate(templatePath);
            File directory = new File(outputDirectory);
            if(directory.exists() || directory.mkdirs()) {
                template.process(dataModel, new FileWriter(fullOutputPath));
            }
            else {
                throw new RuntimeException("Problem while creating directory " + outputDirectory);
            }

        }
    }

    private static String createBasePackageDirectory() {
        String basePackagePath = OUTPUT_PATH + "/" + META_MODEL.getBasePackage().replace('.', '/');
        File basePackageDirectory = new File(basePackagePath);
        if(basePackageDirectory.exists() || basePackageDirectory.mkdirs()) {
            return basePackagePath;
        } else {
            throw new RuntimeException("Problem while creating directory " + basePackagePath);
        }
    }

    private static List<DataModel> getDataModels() {
        return DataManager.getAllDataModels(META_MODEL);
    }

    private static void cleanOutputDirectory() throws IOException {
        try {
            FileUtils.cleanDirectory(new File(OUTPUT_PATH));
        }
        catch(IllegalArgumentException ignored) {} // Don't care if the directory doesn't exist yet, we create it later anyway
    }
}
