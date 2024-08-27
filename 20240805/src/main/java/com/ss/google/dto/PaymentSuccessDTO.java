package com.ss.google.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentSuccessDTO {
	private String aid;
    private String tid;
    private String cid;
    private String sid;
    private String item_name;
    private String partnerOrderId;
    private String partnerUserId;
    private String paymentMethodType;
}
