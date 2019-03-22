package com.test.dubbo.fallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;


/**
 * zuul 路由网关回退机制，即调用的对应服务关闭时回退的响应
 * @author dell
 *
 */

@Component
public class TestFallBack implements ZuulFallbackProvider {

	// 表明是为哪个微服务提供回退
	@Override
	public String getRoute() {
		return "springcloud-ribbo";
	}

	// 回调响应
	@Override
	public ClientHttpResponse fallbackResponse() {
		return new ClientHttpResponse() {

			// headers设定
			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
				MediaType mt = new MediaType("application", "json", Charset.forName("UTF-8"));
				headers.setContentType(mt);
				return headers;
			}

			// 响应体
			@Override
			public InputStream getBody() throws IOException {
				Map<String, Object> map=new HashMap<>();
				map.put("err", "【springcloud-ribbo】 服务关闭，请稍后再试");
				JSONObject json=new JSONObject(map);
				
				return new ByteArrayInputStream(json.toString().getBytes());
			}

			// 状态文本，本例返回的其实就是OK，详见HttpStatus
			@Override
			public String getStatusText() throws IOException {
				return this.getStatusCode().getReasonPhrase();
			}

			// fallback时的状态码
			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.OK;
			}

			// 数字类型的状态码，本例返回的其实就是200，详见HttpStatus
			@Override
			public int getRawStatusCode() throws IOException {
				return this.getStatusCode().value();
			}

			@Override
			public void close() {
			}
		};
	}

}
