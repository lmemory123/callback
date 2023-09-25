package com.example.callback.demos.web;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: TokyoMomao
 * DateTime: 2023-09-04 11:48
 */
@AllArgsConstructor
public enum Type{
    STORE_DEMAND("5X-01", "门店要货"),
    YC_FACTORY_ALLOCATION("5X-Cxx-01", "央厨加工厂间调拨"),
    STORE_TRANSFER_RETURN("5X-Cxx-02", "门店调拨退货"),
    STORE_LOAN_RETURN_TICKET("5X-Cxx-03", "门店借还货退票"),
    SALES_RETURN("5X-Cxx-???", "销售退货"),
    RETURNS_EXCHANGES("退换货", "退换货"),
    // 材料出库
    MATERIAL_OUT("4D-01", "材料出库"), // 11
    MATERIAL_OUT_1("4D-Cxx-01", "职工餐领用"),
    MATERIAL_OUT_2("4D-Cxx-02", "物料消耗"),
    MATERIAL_OUT_3("4D-Cxx-03", "办公领用"),
    MATERIAL_OUT_4("4D-Cxx-04", "福利领用"),
    MATERIAL_OUT_5("4D-Cxx-05", "低值易耗品领用"),
    MATERIAL_OUT_6("4D-Cxx-06", "劳保领用"),
    MATERIAL_OUT_7("4D-Cxx-07", "检测领用"),
    MATERIAL_OUT_8("4D-Cxx-08", "维修领用"),
    MATERIAL_OUT_9("4D-Cxx-09", "研发领用"),
    MATERIAL_OUT_10("4D-Cxx-10", "盘点材料出库"),//11
    MATERIAL_OUT_11("4D-Cxx-11", "业务招待领用"),
    MATERIAL_OUT_12("4D-Cxx-12", "丢弃出库(收市未售完/临期丢弃/备量多变质丢弃)"),
    MATERIAL_OUT_13("4D-Cxx-13", "客诉出库"),
    MATERIAL_OUT_15("4D-Cxx-15", "团队奖领用"),
    MATERIAL_OUT_16("4D-Cxx-16", "促销领用"),
    MATERIAL_OUT_17("4D-Cxx-17", "测试领用"),
    MATERIAL_OUT_18("4D-Cxx-18", "损耗出库(收市转职工餐/临期转职工餐)"),
    MATERIAL_OUT_19("4D-Cxx-19", "积分材料出库"),
    MATERIAL_OUT_20("4D-Cxx-20", "盘点积分材料出库"),
    MATERIAL_OUT_21("4D-Cxx-21", "样品领用"),


    PRODUCT_IN("46-01", "产成品入库"),
    OTHER_OUT("4I-01", "其他出库"),
    OTHER_OUT_1("4I-02", "转库出库"),
    OTHER_OUT_2("4I-03", "盘亏出库"),  //11
    OTHER_OUT_3("4I-04", "组装出库"),
    OTHER_OUT_4("4I-05", "拆卸出库"),
    OTHER_OUT_5("4I-06", "形态转移出库"),
    OTHER_OUT_6("4I-Cxx-01", "门店借出出库"),
    OTHER_OUT_7("4I-Cxx-02", "受托加工原料出库"),

    TRANSFER_STORAGE("4K-01", "巴奴转库单"),
    TRANSFER_STORAGE_1("4K-02", "备料计划转库"),

    OTHER_IN("4A-01", "其他入库"),
    OTHER_IN_1("4A-02", "转库入库"),
    OTHER_IN_2("4A-03", "盘盈入库"), //11
    OTHER_IN_3("4A-04", "组装入库"),
    OTHER_IN_4("4A-05", "拆卸入库"),
    OTHER_IN_5("4A-06", "形态转换入库"),
    OTHER_IN_6("4A-Cxx-01", "门店借入入库"),
    OTHER_IN_7("4A-Cxx-02", "受托加工原料入库"),
    OTHER_IN_8("4A-Cxx-03", "备品备件入库"),
    OTHER_IN_9("4A-Cxx-04", "门店借出还回"),
    OTHER_IN_10("4A-Cxx-05", "样品入库"),



    //入库通知单
    MATERIALS_CG("21-Cxx-01", "检验类物资采购"),
    GENERAL_CG("21-Cxx-02", "普通采购"),
    STORE_CG("21-Cxx-03", "门店采购"),
    CENTRALIZED_CG("21-Cxx-04", "集采分收采购"),
    SUPPLIER_CONSIGNMENT_CG("21-Cxx-05", "供应商寄存采购"),
    DAILY_CG("21-Cxx-06", "日采类订单"),
            ;
    @Getter
    private String key;
    @Getter
    private String value;

    //  MATERIAL_OUT_1("4D-Cxx-01", "职工餐领用"),
    //    MATERIAL_OUT_2("4D-Cxx-02", "物料消耗"),
    //    MATERIAL_OUT_3("4D-Cxx-03", "办公领用"),
    //    MATERIAL_OUT_4("4D-Cxx-04", "福利领用"),
    //    MATERIAL_OUT_5("4D-Cxx-05", "低值易耗品领用"),
    //    MATERIAL_OUT_6("4D-Cxx-06", "劳保领用"),
    //    MATERIAL_OUT_7("4D-Cxx-07", "检测领用"),
    //    MATERIAL_OUT_8("4D-Cxx-08", "维修领用"),
    //    MATERIAL_OUT_9("4D-Cxx-09", "研发领用"),
    // 将这些类型的key放入set中
    public static Set<String> LCCK=Sets.newHashSet(
            MATERIAL_OUT_1.getKey(),
            MATERIAL_OUT_2.getKey(),
            MATERIAL_OUT_3.getKey(),
            MATERIAL_OUT_4.getKey(),
            MATERIAL_OUT_5.getKey(),
            MATERIAL_OUT_6.getKey(),
            MATERIAL_OUT_7.getKey(),
            MATERIAL_OUT_8.getKey(),
            MATERIAL_OUT_9.getKey(),
            MATERIAL_OUT_10.getKey(),
            MATERIAL_OUT_11.getKey(),
            MATERIAL_OUT_12.getKey(),
            MATERIAL_OUT_13.getKey(),
            MATERIAL_OUT_15.getKey(),
            MATERIAL_OUT_16.getKey(),
            MATERIAL_OUT_17.getKey(),
            MATERIAL_OUT_18.getKey(),
            MATERIAL_OUT_19.getKey(),
            MATERIAL_OUT_20.getKey(),
            MATERIAL_OUT_21.getKey()
    );


}
