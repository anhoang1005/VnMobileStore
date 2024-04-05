package com.example.ZVnMobile.service.impl;

import com.example.ZVnMobile.payload.DataResponse;
import com.example.ZVnMobile.payload.request.GetFeeRequest;
import com.example.ZVnMobile.payload.request.TimeGHNRequest;

public interface IGHNService {
	DataResponse getAllProvince();
	DataResponse getAllDistrict(String provinceId);
	DataResponse getAllWard(String districtId);
	DataResponse getService(Integer to_district);
	DataResponse getFee(GetFeeRequest feeRequest);
	DataResponse getTime(TimeGHNRequest ghnRequest);
}
