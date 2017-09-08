package cn.xj.common.model;

import cn.xj.common.tool.IdWorker;
import cn.xj.common.util.ConvertUtil;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Welink on 2017/4/26.
 */
public class BaseInfo implements Serializable {

    private StringBuffer instruction;
    private Map<String, Object> params;
    private List<InstructionBuilder> instructionBuilders;

    public StringBuffer getInstruction() {
        return instruction;
    }

    public void setInstruction(StringBuffer instruction) {
        this.instruction = instruction;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public List<InstructionBuilder> getInstructionBuilders() {
        return instructionBuilders;
    }

    public void setInstructionBuilders(List<InstructionBuilder> instructionBuilders) {
        this.instructionBuilders = instructionBuilders;
    }

    public BaseInfo(List<InstructionBuilder> instructionBuilders) {
        this.instructionBuilders = instructionBuilders;
    }

    public BaseInfo(StringBuffer instruction) {
        this.instruction = instruction;
    }

    public BaseInfo(StringBuffer instruction, Map params) {
        this.instruction = instruction;
        this.params = params;
    }

    @Override
    public String toString() {
        return "BaseInfo{" +
                "instruction=" + instruction +
                ", params=" + params +
                ", instructionBuilders=" + instructionBuilders +
                '}';
    }

    private Object getValue(String key, Map vo) {
        int idx = key.indexOf(".");
        if (idx == -1) {
            return vo.get(key);
        }
        Map map = (Map) vo.get(key.substring(0, idx));
        return this.getValue(key.substring(idx + 1), map);
    }

    private Object mapping(String str, Map vo) {
        int idx = str.indexOf(",");
        return ConvertUtil.convertByType(this.getValue(str.substring(0, idx), vo), str.substring(idx + 1));
    }

    public void doIn(String mark, String key, String type, List<Map> list) {
        StringBuffer buffer = new StringBuffer();
        int len = list.size() - 1;
        if (list != null && len > 0) {
            IdWorker idWorker = new IdWorker(0, 0);
            Long id;
            buffer.append("(");
            for (int i = 0; i < len; i++) {
                id = idWorker.nextId();
                buffer.append("{").append(id).append(",").append(type).append("}");
                this.params.put("" + id, list.get(i).get(key));
            }
            id = idWorker.nextId();
            buffer.append("{").append(id).append(",").append(type).append("})");
            this.params.put("" + id, list.get(len).get(key));
        }
        this.setInstruction(this.instruction.replace(this.instruction.indexOf(mark), this.instruction.indexOf(mark) + mark.length(), buffer.toString()));
    }

    public void load() {
        String instruction = this.instruction == null ? null : this.instruction.toString();
        this.instructionBuilders = Lists.newArrayList();
        if (this.params == null || this.params.size() == 0) {
            this.instructionBuilders.add(new InstructionBuilder(instruction, null));
        } else {
            String[] array = instruction.split("}");
            for (int i = 0; i < array.length; i++) {
                int idx = array[i].indexOf("{");
                if (idx > 0) {
                    if (this.mapping(array[i].substring(idx + 1), this.params) == null) {
                        this.instructionBuilders.add(new InstructionBuilder(array[i].replace(array[i].substring(idx), "null"), null));
                    } else {
                        this.instructionBuilders.add(new InstructionBuilder(array[i].replace(array[i].substring(idx), ""), this.mapping(array[i].substring(idx + 1), this.params)));
                    }
                } else {
                    this.instructionBuilders.add(new InstructionBuilder(array[i], null));
                }
            }
        }
    }

}
