package cn.xj.common.container;

import cn.xj.common.annotation.Joiner;
import cn.xj.common.annotation.Meta;
import cn.xj.common.annotation.Model;
import cn.xj.common.config.BaseConfig;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by Welink on 2017/4/26.
 */
@Component
@ConfigurationProperties(prefix = "contextContainer")
public class ContextContainer {

    @Autowired
    private BaseConfig baseConfig;

    //类容器
    private Map<String, Class> classContainer = Maps.newHashMap();
    //域容器
    private Map<String, Model> modelContainer = Maps.newHashMap();
    //字段映射容器
    private Map<String, Map<String, String>> mappingContainer = Maps.newHashMap();
    //元数据容器
    private Map<String, Map<String, Meta>> metaContainer = Maps.newHashMap();
    //关联配置容器
    private Map<String, Map<String, Joiner>> joinerContainer = Maps.newHashMap();
    //SQL容器
    private Map<String, Map<String, StringBuffer>> instructionContainer = Maps.newHashMap();

    public Map<String, Class> getClassContainer() {
        return classContainer;
    }

    public void setClassContainer(Map<String, Class> classContainer) {
        this.classContainer = classContainer;
    }

    public Map<String, Model> getModelContainer() {
        return modelContainer;
    }

    public void setModelContainer(Map<String, Model> modelContainer) {
        this.modelContainer = modelContainer;
    }

    public Map<String, Map<String, String>> getMappingContainer() {
        return mappingContainer;
    }

    public void setMappingContainer(Map<String, Map<String, String>> mappingContainer) {
        this.mappingContainer = mappingContainer;
    }

    public Map<String, Map<String, Meta>> getMetaContainer() {
        return metaContainer;
    }

    public void setMetaContainer(Map<String, Map<String, Meta>> metaContainer) {
        this.metaContainer = metaContainer;
    }

    public Map<String, Map<String, Joiner>> getJoinerContainer() {
        return joinerContainer;
    }

    public void setJoinerContainer(Map<String, Map<String, Joiner>> joinerContainer) {
        this.joinerContainer = joinerContainer;
    }

    public Map<String, Map<String, StringBuffer>> getInstructionContainer() {
        return instructionContainer;
    }

    public void setInstructionContainer(Map<String, Map<String, StringBuffer>> instructionContainer) {
        this.instructionContainer = instructionContainer;
    }

    public void loadContext(String className) throws Exception {
        Class clazz = Class.forName(baseConfig.getBeanPath() + "." + className);
        this.loadContext(clazz);
    }

    public void loadContext(Class<?> clazz) throws Exception {
        String key = clazz.getSimpleName();
        if (this.classContainer.get(key) != null) {
            return;
        }
        this.classContainer.put(key, clazz);
        //获取类上的注解
        Model model = clazz.getAnnotation(Model.class);
        this.modelContainer.put(key, model);
        //获取字段上的注解
        Field[] fields = clazz.getDeclaredFields();
        Map<String, Meta> metaMap = Maps.newHashMap();
        Map<String, Joiner> joinerMap = Maps.newHashMap();
        Map<String, String> mappingMap = Maps.newHashMap();
        Map<String, StringBuffer> instructionMap = Maps.newHashMap();
        Meta meta;
        Joiner joiner;
        String field, type, pKey;
        String mapping = null, column = null;
        StringBuffer prefix = new StringBuffer(), suffix = new StringBuffer();
        for (Field f : fields) {
            joiner = f.getAnnotation(Joiner.class);
            type = f.getType().getSimpleName();
            field = f.getName();
            meta = f.getAnnotation(Meta.class);
            if (joiner == null) {
                if (meta != null && !"".equals(meta.column())) {
                    column = meta.column();
                } else {
                    column = field;
                }
                mapping = "{" + field + "," + type.substring(type.lastIndexOf(".") + 1) + "}";
                mappingMap.put(field, column + "=" + mapping);
            }
            if (meta != null && meta.pKey()) {
                pKey = "".equals(meta.column()) ? field : meta.column();
                StringBuffer update = new StringBuffer("update " + this.modelContainer.get(key).table() + " set @Update where " + pKey + "=" + mapping);
                StringBuffer delete = new StringBuffer("delete from " + this.modelContainer.get(key).table() + " where " + pKey + "=" + mapping);
                instructionMap.put("update", update);
                instructionMap.put("delete", delete);
            }
            metaMap.put(field, meta);
            prefix.append("," + column);
            suffix.append("," + mapping);
        }
        StringBuffer insert = new StringBuffer("insert into " + this.modelContainer.get(key).table() +
                " (" + prefix.substring(1) + ") values (" + suffix.substring(1) + ")");
        instructionMap.put("insert", insert);
        this.mappingContainer.put(key, mappingMap);
        this.metaContainer.put(key, metaMap);
        this.joinerContainer.put(key, joinerMap);
        this.instructionContainer.put(key, instructionMap);
        for (Field f : fields) {
            joiner = f.getAnnotation(Joiner.class);
            if (joiner != null) {
                this.loadContext(joiner.refClass());
                joinerMap.put(joiner.refClass(), joiner);
            }
        }
        this.joinerContainer.put(key, joinerMap);
    }

    public static void main(String[] args) throws Exception {
    }

}
