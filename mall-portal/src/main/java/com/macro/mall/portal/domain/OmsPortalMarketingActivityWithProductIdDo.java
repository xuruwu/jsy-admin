package com.macro.mall.portal.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 商品营销活动信息
 * Created by ruiwu.xu on 2019/01/19.
 */
@ApiModel(value= "订单模块-商品营销活动Dto",description = "商品营销活动Dto:OmsPortalMarketingActivityWithProductIdDto")
@Getter
@Setter
public class OmsPortalMarketingActivityWithProductIdDo extends  SmsMarketingActivities{
    @ApiModelProperty(value = "商品ID")
    private Long productId;
    @ApiModelProperty(value = "营销活动规则")
    private SmsMarketingActivityRule smsMarketingActivityRule;
}