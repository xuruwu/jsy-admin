package com.macro.mall.portal.service;

import java.util.List;

/**
 * 用户地址管理Service
 * Created by macro on 2018/8/28.
 */
public interface UmsMemberReceiveAddressService {
    /**
     * 添加收货地址,并返回地址信息
     */
    UmsMemberReceiveAddress add(UmsMemberReceiveAddress address);

    /**
     * 删除收货地址
     * @param id 地址表的id
     */
    int delete(Long id);

    /**
     * 修改收货地址
     * @param id 地址表的id
     * @param address 修改的收货地址信息
     */
    int update(Long id, UmsMemberReceiveAddress address);

    /**
     * 获取地址详情
     * @param id 地址id
     */
    UmsMemberReceiveAddress getItem(Long id);

    /**
     * 返回当前用户的所有的收货地址
     */
    List<UmsMemberReceiveAddress> list(Long memberId);

    /**
     * 返回当前用户的默认收货地址
     */
    UmsMemberReceiveAddress getDefault(Long memberId);
}