package cn.loverot.base.service;

import cn.loverot.base.constant.e.TableEnum;
import cn.loverot.base.entity.BaseEntity;

import java.util.List;
import java.util.Map;

public interface IBaseService <E> {

    void alterTable(String var1, Map var2, TableEnum var3);

    int countBySQL(String var1, Map var2);

    void createTable(String var1, Map<Object, List> var2);

    void delete(int[] var1);

    void deleteBySQL(String var1, Map var2);

    void deleteEntity(BaseEntity var1);

    void dropTable(String var1);

    Object excuteSql(String var1);

    <E> E getEntity(BaseEntity var1);

    <E> BaseEntity getEntity(int var1);

    void insertBySQL(String var1, Map var2);

    List<E> query(BaseEntity var1);

    List<E> queryAll();

    List queryBySQL(String var1, List<String> var2, Map var3);

    List queryBySQL(String var1, List<String> var2, Map var3, Integer var4, Integer var5);


    void saveBatch(List var1);

    int saveEntity(BaseEntity var1);

    void updateBySQL(String var1, Map var2, Map var3);

    void updateEntity(BaseEntity var1);
}
