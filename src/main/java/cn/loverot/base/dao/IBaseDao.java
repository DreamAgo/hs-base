package cn.loverot.base.dao;

import cn.loverot.base.entity.BaseEntity;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IBaseDao<E extends Serializable>{
    void alterTable(@Param("table") String var1, @Param("fileds") Map var2, @Param("type") String var3);

    int countBySQL(@Param("table") String var1, @Param("wheres") Map var2);

    void createTable(@Param("table") String var1, @Param("fileds") Map<Object, List> var2);

    void deleteBySQL(@Param("table") String var1, @Param("wheres") Map var2);

    void dropTable(@Param("table") String var1);

    List excuteSql(@Param("sql") String var1);

    void insertBySQL(@Param("table") String var1, @Param("fields") Map var2);

    List queryBySQL(@Param("table") String var1, @Param("fields") List<String> var2, @Param("wheres") Map var3, @Param("begin") Integer var4, @Param("end") Integer var5, @Param("order") String var6);

    void updateBySQL(@Param("table") String var1, @Param("fields") Map var2, @Param("wheres") Map var3);

    void delete(@Param("ids") int[] var1);

    void deleteByEntity(BaseEntity var1);

    BaseEntity getEntity(Integer var1);

    <E> E getByEntity(BaseEntity var1);

    List<E> query(BaseEntity var1);

    List<E> queryAll();


    void saveBatch(@Param("list") List var1);

    int saveEntity(BaseEntity var1);

    void updateEntity(BaseEntity var1);
}
