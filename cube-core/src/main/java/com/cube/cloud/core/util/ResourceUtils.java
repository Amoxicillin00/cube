package com.cube.cloud.core.util;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.ClassUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-10 15:35
 */
public class ResourceUtils {

    private static final Logger logger = LoggerFactory.getLogger(ResourceUtils.class);

    /**
     * 获取资源的根路径
     * @return String
     */
    public static String getResourceRootPath() {
        try {
            Resource[] resources = new PathMatchingResourcePatternResolver()
                    .getResources(ResourceLoader.CLASSPATH_URL_PREFIX);
            for (Resource resource : resources) {
                if (resource.exists()) {
                    String rootPath = resource.getFile().getAbsolutePath().replace("\\", "/");
                    File dir = new File(rootPath);
                    if (!dir.exists() || !dir.isDirectory()) {
                        dir.mkdirs();
                    }
                    return rootPath;
                }
            }
            return "";
        } catch (IOException e) {
            return "";
        }
    }

    /**
     * 将类名称转换为瓷源路径
     * @param className 类名称
     * @return String
     */
    public static String convertClassNameToResourcePath(String className) {
        return ClassUtils.convertClassNameToResourcePath(className);
    }

    /**
     * 读取资源列表
     * @param resPath 资源路径
     * @param resClass 资源类型
     * @return List<TRes>
     * @throws IOException IO异常
     */
    /*public static <TRes> List<TRes> readResList(String resPath, Class<TRes> resClass) {
        String json = readResString(resPath);
        if (StringUtils.isNullOrBlank(json)) {
            return new ArrayList<>();
        }
        return JsonUtils.parseList(json, resClass);
    }*/

    /**
     * 读取资源对象
     * @param resPath  资源路径
     * @param resClass 资源类型
     * @return TRes
     * @throws IOException IO异常
     */
    /*public static <TRes> TRes readResObject(String resPath, Class<TRes> resClass) {
        String json = readResString(resPath);
        return JsonUtils.parseObject(json, resClass);
    }*/

    /**
     * 读取资源字符
     * @param resPath 资源路径
     * @return String
     */
    public static String readResString(String resPath) {
        String json = "";
        InputStream input = null;
        try {
            input = ClassLoader.getSystemResourceAsStream(resPath);
            if (input != null) {
                json = IOUtils.toString(input, StandardCharsets.UTF_8);
            } else {
                logger.error("读取资源[" + resPath + "]的结果:null");
            }
        } catch (IOException e) {
            //TODO 抛出异常
            System.out.println("抛出异常！读取资源[" + resPath + "]出错:\r\n" + e.getMessage());
            logger.error("读取资源[" + resPath + "]出错:\r\n" + e.getMessage());
            //throw ExceptionUtils.throwSystemException(e.getMessage(), e);
        } finally {
            IOUtils.closeQuietly(input);
        }
        return json;
    }
}
