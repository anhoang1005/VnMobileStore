package com.example.ZVnMobile.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.ZVnMobile.payload.DataResponse;
import com.example.ZVnMobile.payload.request.GetFeeRequest;
import com.example.ZVnMobile.payload.request.TimeGHNRequest;
import com.example.ZVnMobile.service.impl.IGHNService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GHNService implements IGHNService {

	@Value("${ghn.privateToken}")
	private String ghnToken;

	@Value("${ghn.shopID}")
	private String shopId;

	@Value("${ghn.shopDistrict}")
	private String shopDistrict;

	@Value("${ghn.shopWard}")
	private String shopWard;

	private RestTemplate restTemplate;

	public GHNService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public DataResponse getAllProvince() {
		DataResponse dataResponse = new DataResponse();
		String apiUrl = "https://online-gateway.ghn.vn/shiip/public-api/master-data/province";

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("token", ghnToken);

			ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, new HttpEntity<>(headers),
					String.class);
			if (response.getStatusCode() == HttpStatus.OK) {
				ObjectMapper objectMapper = new ObjectMapper();
				Object dataObject = objectMapper.readValue(response.getBody(), Object.class);

				dataResponse.setData(dataObject);
				dataResponse.setSuccess(true);
			} else if (response.getStatusCode() == HttpStatus.FORBIDDEN) {
				dataResponse.setData("403");
				dataResponse.setSuccess(false);
			} else if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
				dataResponse.setData("401");
				dataResponse.setSuccess(false);
			} else {
				dataResponse.setData("Loi");
				dataResponse.setSuccess(false);
			}
		} catch (Exception e) {
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setMessage("Error");
			dataResponse.setSuccess(false);
		}

		return dataResponse;
	}

	@Override
	public DataResponse getAllDistrict(String proviceId) {
		DataResponse dataResponse = new DataResponse();
		String apiUrl = "https://online-gateway.ghn.vn/shiip/public-api/master-data/district";
		String urlWithParams = apiUrl + "?province_id=" + proviceId;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("token", ghnToken);
			ResponseEntity<String> response = restTemplate.exchange(urlWithParams, HttpMethod.GET,
					new HttpEntity<>(headers), String.class);
			if (response.getStatusCode() == HttpStatus.OK) {
				ObjectMapper objectMapper = new ObjectMapper();
				Object dataObject = objectMapper.readValue(response.getBody(), Object.class);

				dataResponse.setData(dataObject);
				dataResponse.setSuccess(true);
			} else if (response.getStatusCode() == HttpStatus.FORBIDDEN) {
				dataResponse.setData("403");
				dataResponse.setSuccess(false);
			} else if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
				dataResponse.setData("401");
				dataResponse.setSuccess(false);
			} else {
				dataResponse.setData("Loi");
				dataResponse.setSuccess(false);
			}
		} catch (Exception e) {
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setMessage("Error");
			dataResponse.setSuccess(false);
		}

		return dataResponse;
	}

	@Override
	public DataResponse getAllWard(String districtId) {
		DataResponse dataResponse = new DataResponse();
		String apiUrl = "https://online-gateway.ghn.vn/shiip/public-api/master-data/ward";
		String urlWithParams = apiUrl + "?district_id=" + districtId;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("token", ghnToken);
			ResponseEntity<String> response = restTemplate.exchange(urlWithParams, HttpMethod.GET,
					new HttpEntity<>(headers), String.class);
			if (response.getStatusCode() == HttpStatus.OK) {
				ObjectMapper objectMapper = new ObjectMapper();
				Object dataObject = objectMapper.readValue(response.getBody(), Object.class);

				dataResponse.setData(dataObject);
				dataResponse.setSuccess(true);
			} else if (response.getStatusCode() == HttpStatus.FORBIDDEN) {
				dataResponse.setData("403");
				dataResponse.setSuccess(false);
			} else if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
				dataResponse.setData("401");
				dataResponse.setSuccess(false);
			} else {
				dataResponse.setData("Loi");
				dataResponse.setSuccess(false);
			}
		} catch (Exception e) {
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setMessage("Error");
			dataResponse.setSuccess(false);
		}

		return dataResponse;
	}

	@Override
	public DataResponse getService(Integer to_district) {
		DataResponse dataResponse = new DataResponse();
		String urlWithParams = "https://online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/available-services"
				+ "?shop_id=" + shopId
				+ "&from_district=" + shopDistrict
				+ "&to_district=" + to_district;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("token", ghnToken);
			ResponseEntity<String> response = restTemplate.exchange(urlWithParams, HttpMethod.GET,
					new HttpEntity<>(headers), String.class);
			if (response.getStatusCode() == HttpStatus.OK) {
				ObjectMapper objectMapper = new ObjectMapper();
				Object dataObject = objectMapper.readValue(response.getBody(), Object.class);

				dataResponse.setData(dataObject);
				dataResponse.setSuccess(true);
			} else if (response.getStatusCode() == HttpStatus.FORBIDDEN) {
				dataResponse.setData("403");
				dataResponse.setSuccess(false);
			} else if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
				dataResponse.setData("401");
				dataResponse.setSuccess(false);
			} else {
				dataResponse.setData("Loi");
				dataResponse.setSuccess(false);
			}
		} catch (Exception e) {
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setMessage("Error");
			dataResponse.setSuccess(false);
		}

		return dataResponse;
	}

	@Override
	public DataResponse getFee(GetFeeRequest feeRequest) {
		DataResponse dataResponse = new DataResponse();
		String apiParam = "https://online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/fee" + "?service_id="
				+ feeRequest.getService_id() + "&insurance_value=" + feeRequest.getInsurance_value()
				+ "&from_district_id=" + shopDistrict + "&to_district_id=" + feeRequest.getTo_district_id()
				+ "&to_ward_code=" + feeRequest.getTo_ward_code() + "&height=" + feeRequest.getHeight() + "&length="
				+ feeRequest.getLength() + "&weight=" + feeRequest.getWeight() + "&width=" + feeRequest.getWidth();

		// System.out.println(apiParam);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("token", ghnToken);
			ResponseEntity<String> response = restTemplate.exchange(apiParam, HttpMethod.GET, new HttpEntity<>(headers),
					String.class);
			if (response.getStatusCode() == HttpStatus.OK) {
				ObjectMapper objectMapper = new ObjectMapper();
				Object dataObject = objectMapper.readValue(response.getBody(), Object.class);

				dataResponse.setData(dataObject);
				dataResponse.setSuccess(true);
			} else if (response.getStatusCode() == HttpStatus.FORBIDDEN) {
				dataResponse.setData("403");
				dataResponse.setSuccess(false);
			} else if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
				dataResponse.setData("401");
				dataResponse.setSuccess(false);
			} else {
				dataResponse.setData("Loi");
				dataResponse.setSuccess(false);
			}
		} catch (Exception e) {
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setMessage("Error");
			dataResponse.setSuccess(false);
		}
		return dataResponse;
	}

	@Override
	public DataResponse getTime(TimeGHNRequest ghnRequest) {
		DataResponse dataResponse = new DataResponse();
		String apiParam = "https://online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/leadtime"
				+ "?from_district_id=" + shopDistrict
				+ "&from_ward_code=" + shopWard 
				+ "&to_district_id=" + ghnRequest.getTo_district_id()
				+ "&to_ward_code=" + ghnRequest.getTo_ward_code()
				+ "&service_id=" + ghnRequest.getService_id();

		// System.out.println(apiParam);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("token", ghnToken);
			ResponseEntity<String> response = restTemplate.exchange(apiParam, HttpMethod.GET, new HttpEntity<>(headers),
					String.class);
			if (response.getStatusCode() == HttpStatus.OK) {
				ObjectMapper objectMapper = new ObjectMapper();
				Object dataObject = objectMapper.readValue(response.getBody(), Object.class);

				dataResponse.setData(dataObject);
				dataResponse.setSuccess(true);
			} else if (response.getStatusCode() == HttpStatus.FORBIDDEN) {
				dataResponse.setData("403");
				dataResponse.setSuccess(false);
			} else if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
				dataResponse.setData("401");
				dataResponse.setSuccess(false);
			} else {
				dataResponse.setData("Loi");
				dataResponse.setSuccess(false);
			}
		} catch (Exception e) {
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setMessage("Error");
			dataResponse.setSuccess(false);
		}
		return dataResponse;
	}

}
