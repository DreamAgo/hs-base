package cn.loverot.base.constant.e;

public enum TableEnum implements BaseEnum {
    /**
     * 添加
     */
    ALTER_ADD("add"),
    /**
     * 修改
     */
    ALTER_MODIFY("modify"),
    /**
     * 删除
     */
    ALTER_DROP("drop");

    private String obj;

    private TableEnum(String obj) {
        this.obj = obj;
    }

    @Override
    public int toInt() {
        return 0;
    }

    @Override
    public String toString() {
        return this.obj.toString();
    }
}
