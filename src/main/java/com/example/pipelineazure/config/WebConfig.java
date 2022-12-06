package com.example.pipelineazure.config;


import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.TransformedResource;

import java.io.*;

import static java.nio.charset.StandardCharsets.UTF_8;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler(
        "/**"
      )
      .addResourceLocations("classpath:/static/")
      .setCachePeriod(null)
      .resourceChain(true)
      .addResolver(new ResourceResolver());
  }
  private class ResourceResolver extends PathResourceResolver{
    private TransformedResource transformedResource(final Resource resource) throws IOException {
      String fileContent;
      try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
        fileContent = FileCopyUtils.copyToString(reader);
      } catch (IOException e) {
        throw new UncheckedIOException(e);
      }
      fileContent = fileContent.replace("#context-path#","//");
      return new TransformedResource(resource, fileContent.getBytes());
    }

    @Override
    protected Resource getResource(String resourcePath, Resource location) throws IOException {
      Resource requestedResource = location.createRelative(resourcePath);

      if (requestedResource.exists() && requestedResource.isReadable()) {
        if (resourcePath.contains("index.html")) {
          return transformedResource(requestedResource);
        }
        return requestedResource;
      }
      if (("/" + resourcePath).startsWith("/api")) {
        return null;
      }
      requestedResource = location.createRelative("index.html");
      if (requestedResource.exists() && requestedResource.isReadable()) {
        return transformedResource(requestedResource);
      }
      return null;
    }
  }
}
