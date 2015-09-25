package com.hxyt.utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.ParseException;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.preference.Preference;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hxyt.AppContent;
import com.hxyt.ProjectCommand;
import com.hxyt.bean.HeadJson;
import com.hxyt.bean.Row;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.PreferencesCookieStore;



/**
 * * @author 作者 陈修园:
 * 
 * @date 创建时间：2015-5-22 上午11:42:10
 * @version 1.0
 * @parameter 返回的javabeen，RequestParams,dataMap,PreferencesCookieStore
 *            preferencesCookieStore
 * @since
 * @return 返回list javabeen
 */
public class HttpJsonTool {
	private HttpUtils http;
	private Gson gson;
	private boolean flag = false;
	private PreferencesCookieStore preferencesCookieStore;
	private Handler halHandler;
	// 图片handle
	private Handler imageHandler;
	//log日志是上传文件
	private Handler logHandler;
	
	// private String url;
	private Context context;
	private Handler halHandlerRow;

	public HttpJsonTool(Context context) {
		// this.result_Class=result_class;
		// this.params=params;

		// preferencesCookieStore=preferencesCookieStore;
		http = new HttpUtils();
		gson = new Gson();
		this.context = context;
		// StartWork();
		// List<> data=new ArrayList<>;
	}

	/**
	 * 
	 * @param context
	 *            上下文
	 * @param flag
	 *            是否要保存回话 true 保存自动保存到Appcontent
	 */
	public HttpJsonTool(Context context, boolean flag) {
		gson = new Gson();
		this.context = context;
		this.flag = flag;
		http = new HttpUtils();
		preferencesCookieStore = new PreferencesCookieStore(context);
	}

	/**
	 * 
	 * @param url
	 *            带有Https协议的
	 * @param T
	 * @param dataMap
	 */
	// public void StartHttpsWork(String url,final Class T , Map<String, Object>
	// dataMap){
	// JsonRequest jr = JsonRequest.getInit();
	// jr.setData(dataMap);
	// //dataMap.put("wd", "电影");
	// String json = gson.toJson(dataMap);
	// RequestParams params = new RequestParams();
	// params.addBodyParameter("jsonParam", json);
	// SSLSocketFactory
	// sslsocketfactory=CustomerSocketFactory.getSocketFactory(context);
	// //Type type=new TypeToken<List<T>>() {
	// //}.getType();
	// if (sslsocketfactory==null) {
	// Log.v("sslsocketfactory", "sslsocketfactory--》是空");
	// return;
	// }
	// http.configSSLSocketFactory(sslsocketfactory);
	// http.send(HttpMethod.POST, url, params,new RequestCallBack<String>() {
	//
	// @Override
	// public void onFailure(HttpException arg0, String arg1) {
	// // TODO Auto-generated method stub
	// Log.v("HTTPS连接失败", ""+arg0.getMessage());
	// Log.v("HTTPS连接失败", "HTTPS连接失败");
	// }
	//
	// @Override
	// public void onSuccess(ResponseInfo<String> arg0) {
	// // TODO Auto-generated method stub
	// Log.v("HTTPS连接成功", "HTTPS连接成功");
	// Log.v("获得12306的信息", ""+arg0.result);
	// }
	// });
	//
	// }
	//
	//

	public void StartWorkUpFile(Object data, String url, final Type type,
			Map<String, String> filename) {

		String json = gson.toJson(data);
		Log.v("json", json);
		RequestParams params = new RequestParams();
		// params.addHeader(name, value);
		// params.setContentType(contentType)
		// RequestParams params=new req
		params.addHeader("Content-Type",
				"multipart/form-data;boundary=*************************7d");
		// params.setHeader("", "");
		// params.setContentType("multipart/form-data;boundary=*************************7d");
		// params.setBodyEntity(bodyEntity);
		if (filename != null && (filename.size() > 0)) {
			// params.addHeader("Content-Type","multipart/form-data");
			// params.setContentType("multipart/form-data");
			for (Map.Entry<String, String> entry : filename.entrySet()) {
				params.addBodyParameter(entry.getKey(),
						new File(entry.getValue()));
				Log.v("filename", "filename---->" + entry.getKey());
			}
		}
		// String root=SD_Finder.GetPaht();
		// if (root==null) {
		// Toast.makeText(context, "SD没有被发现请检查SD卡是否存在",
		// Toast.LENGTH_LONG).show();
		// }
		// File f=new File(root+GagApi.UP_LOAD_FILE);
		// if (f.exists()) {
		// params.addBodyParameter("unusefile",f);
		// }else {
		// try {
		// f.createNewFile();
		// params.addBodyParameter("unusefile",f);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// File file=new f
		params.addBodyParameter("jsonParam", json);
		if (AppContent.getPreferencesCookieStore() != null) {
			Log.v("CookieStore不为空", "CookieStore不为空");
			http.configCookieStore(AppContent.getPreferencesCookieStore());
		}

		// http.configSSLSocketFactory(sslSocketFactory);
		http.send(HttpRequest.HttpMethod.POST, url, params,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						Toast.makeText(context, "连接网络失败请检查网络...",
								Toast.LENGTH_LONG).show();
						halHandler.sendEmptyMessage(0);
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// TODO Auto-generated method stub

						Gson gson = new Gson();
						String jsonString = responseInfo.result;
						Log.v("url连接上后获得的URL", "" + jsonString);
						try {
							HeadJson headJson = gson.fromJson(new JSONObject(
									jsonString).getJSONObject("head") + "",
									HeadJson.class);
							if ("200".equals(headJson.getMessageStaus())) {
								// 连接成功保存会话
								Log.e("是否保存会话", flag + "");
								if (flag) {
									DefaultHttpClient httpClient = (DefaultHttpClient) http
											.getHttpClient();
									Log.v("cooke", "保存cooke");
									List<Cookie> cookies = httpClient
											.getCookieStore().getCookies();
									for (int i = 0; i < cookies.size(); i++) {
										Cookie cookie = cookies.get(i);
										System.out.println(cookie);
										preferencesCookieStore
												.addCookie(cookie);
										Log.v("cooke", "保存cooke");
									}
									AppContent
											.setPreferencesCookieStore(preferencesCookieStore);
								}
								// 连接成功保存会话
								Log.v("上传成功！", "上传成功！");
								Log.v("获得的全部json", new JSONObject(jsonString)
										+ "");
								if (type != null) {

									Log.v("获得的json",
											new JSONObject(jsonString)
													.getJSONArray("data") + "");
									List<?> data = gson.fromJson(
											new JSONObject(jsonString)
													.getJSONArray("data") + "",
											type);
									Message msg = Message.obtain();
									msg.what = 1;
									msg.obj = data;
									halHandler.sendMessage(msg);
								} else {
									Log.v("tyep是空", "是空");
									Message msg = Message.obtain();
									msg.what = 1;
									msg.obj = headJson.getMessageContent();
									halHandler.sendMessage(msg);
								}
							} else {
								Log.v("操作失败", "headJson.getMessageContent()"
										+ headJson.getMessageContent());
								// Toast.makeText(context,
								// headJson.getMessageContent(),
								// Toast.LENGTH_LONG).show();
								Message msg = Message.obtain();
								msg.what = 0;
								msg.obj = headJson.getMessageContent();
								halHandler.sendMessage(msg);
							}
						} catch (JsonSyntaxException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				});
	}

	/**
	 * \
	 * 
	 * @param data
	 *            上传的服务器的数据可以是javabeen
	 * @param url
	 *            服务器的路径url地址
	 * @param type
	 *            返回数据是list<javabeen>的type 可以为空如果是空智慧返回1 或者其他 1表示操作成功
	 * @param file
	 *            上传服务器的文件可以是空 如果是空那么就不上传文件了
	 */
	public void StartWork(Object data, String url, final Type type,
			Map<String, String> filename) {

		String json = gson.toJson(data);
		Log.v("json", json);
		RequestParams params = new RequestParams();

		if (filename != null && (filename.size() > 0)) {
			for (Map.Entry<String, String> entry : filename.entrySet()) {
				params.addBodyParameter(entry.getKey(),
						new File(entry.getValue()));
				Log.v("filename", "filename---->" + entry.getKey());
			}
		}
		params.addBodyParameter("jsonParam", json);
		if (!flag) {

			if (AppContent.getPreferencesCookieStore() != null) {
				Log.v("不为空", "不为空");
				http.configCookieStore(AppContent.getPreferencesCookieStore());
				PreferencesCookieStore preferencesCookieStore = AppContent
						.getPreferencesCookieStore();
				if (preferencesCookieStore == null) {

				}
				List<Cookie> cookies = preferencesCookieStore.getCookies();
				for (int i = 0; i < cookies.size(); i++) {
					Log.v("---------", "----------");
					System.out.print(cookies);
				}
			}
		}
		Log.e("url", url);

		// http.configSSLSocketFactory(sslSocketFactory);
		http.send(HttpRequest.HttpMethod.POST, url, params,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						if (!arg1.equals("Not Found")) {
							// TODO Auto-generated method stub
							// Toast.makeText(context, "连接网络失败请检查网络...",
							// Toast.LENGTH_LONG).show();
							Log.e("连接报错---》", arg1);
							Message message = Message.obtain();
							message.what = 0;
							message.obj = "连接网络失败请检查网络...";
							halHandler.sendMessage(message);
						}
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// TODO Auto-generated method stub

						Gson gson = new Gson();
						String jsonString = responseInfo.result;
						Log.v("url连接上后获得的URL", "" + jsonString);
						try {
							HeadJson headJson = gson.fromJson(new JSONObject(
									jsonString).getJSONObject("head") + "",
									HeadJson.class);
							if ("200".equals(headJson.getMessageStaus())) {
								// 连接成功保存会话
								Log.e("连接上", "222");
								if (flag) {
									Log.e("连接上2", "保存会话2");
									DefaultHttpClient httpClient = (DefaultHttpClient) http
											.getHttpClient();
									if (httpClient == null) {
										Log.e("httpClient", "是空");
									}
									List<Cookie> cookies = httpClient
											.getCookieStore().getCookies();
									Log.e("cookies的长度", "" + cookies.size());
									for (int i = 0; i < cookies.size(); i++) {
										Cookie cookie = cookies.get(i);
										System.out.println(cookie);
										preferencesCookieStore
												.addCookie(cookie);
										Log.e("保存数据Cookie", "Cookie保存数据");
									}
									AppContent
											.setPreferencesCookieStore(preferencesCookieStore);
								}
								// 返回数据总数
								Row row = gson.fromJson(new JSONObject(
										jsonString).getJSONObject("row") + "",
										Row.class);
								Message msg = Message.obtain();
								msg.what = 1;
								msg.obj = row;
								if (halHandlerRow != null) {
									halHandlerRow.sendMessage(msg);
								}

								// 连接成功保存会话
								Log.v("上传成功！", "上传成功！");
								Log.v("获得的全部json", new JSONObject(jsonString)
										+ "");
								if (type != null) {

									Log.v("获得的json",
											new JSONObject(jsonString)
													.getJSONArray("data") + "");

									// 返回数据
									List<?> data = gson.fromJson(new JSONObject(jsonString).getJSONArray("data") + "",type);
									Message msg2 = Message.obtain();
									msg2.what = 1;
									msg2.obj = data;
									halHandler.sendMessage(msg2);
								} else {
								//	Log.v("tyep是空", "是空");
									Message msge = Message.obtain();
									msge.what = 1;
									msge.obj = headJson.getMessageContent();
									halHandler.sendMessage(msge);
								}
							} else if ("7500".equals(headJson.getMessageStaus())) {
								// 会话没有丢失
//								Toast.makeText(context, "司机师傅对不起,您登录超时请从新登录。",
//										Toast.LENGTH_LONG).show();
//								Intent intent = new Intent(context
//										.getApplicationContext(),
//										UserLoginActivity.class);
//								context.startActivity(intent);
							} else {
								// Log.v("操作失败", "headJson.getMessageContent()"
								// + headJson.getMessageContent());
								// Toast.makeText(context,
								// headJson.getMessageContent(),
								// Toast.LENGTH_LONG).show();
								Message msg = Message.obtain();
								msg.what = 0;
								msg.obj = headJson.getMessageContent();
								halHandler.sendMessage(msg);
							}
						} catch (JsonSyntaxException e) {
							// TODO Auto-generated catch block
							//如果出现异常那么重新登录为了解决会话没有的问题
//							Intent intent = new Intent(context
//									.getApplicationContext(),
//									UserLoginActivity.class);
//
//							intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//							context.getApplicationContext().startActivity(
//									intent);
//							e.printStackTrace();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							//如果出现异常那么重新登录为了解决会话没有的问题
//							Intent intent = new Intent(context
//									.getApplicationContext(),
//									UserLoginActivity.class);
//
//							intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//							context.getApplicationContext().startActivity(
//									intent);
//							e.printStackTrace();
						}

					}
				});
	}

	public void UpLoadFile(String path){
		RequestParams params = new RequestParams();
		params.addBodyParameter("errorLog",new File(path));
		http.configCookieStore(AppContent.getPreferencesCookieStore());
		http.send(HttpRequest.HttpMethod.POST, ProjectCommand.ProjectFolder.UP_LOAD_LOG_FILE, params,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						if (logHandler!=null) {
							logHandler.sendEmptyMessage(1);
						}
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						if (logHandler!=null) {
							logHandler.sendEmptyMessage(1);
						}
					}
				});
	}
	
	
	/**
	 * 
	 * @Title: DownLoadFile
	 * @Description: TODO(下载文件)
	 * @param @param url 参数说明
	 * @return void 返回类型
	 * @throws
	 */
	public void DownLoadFile(final String url) {
		// http.download(url, target, callback);
		final String sd_path;
		final String fileName;
		 String rootpath="" ;
		if (SDCardUtils.isSDCardEnable()) {
				rootpath= SDCardUtils.getSDCardPath();
				Message msg = Message.obtain();
				msg.what = 0;
				msg.obj = "SD卡不存在";
				if (imageHandler != null) {
					imageHandler.sendMessage(msg);
				}
				return;			
		}
		fileName = url.substring(url.lastIndexOf(File.separator) + 1);
		sd_path = rootpath + ProjectCommand.ProjectFolder.IMAGE_FILE_PATH + fileName;
		http.download(url, sd_path, new RequestCallBack<File>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Message msg = Message.obtain();
				msg.what = 0;
			//	Log.v("下载失败错误CODE", arg1);
				msg.obj = "下载失败";
				if (imageHandler != null) {
					imageHandler.sendMessage(msg);
				}
				// imageHandler.sendMessage(msg);
			}

			@Override
			public void onSuccess(ResponseInfo<File> file) {
				// TODO Auto-generated method stub
				Message msg = Message.obtain();
				msg.what = 1;
				msg.obj = file.result.getPath();
				if (imageHandler != null) {
					imageHandler.sendMessage(msg);
				}
				// imageHandler.sendMessage(msg);
			}

		});
	}

	public void setImageLister(Handler handler) {
		this.imageHandler = handler;
	}

	public void SetOnLister(Handler hander) {
		this.halHandler = hander;
	}

	public void SetRowOnLister(Handler halHandlerRow) {
		this.halHandlerRow = halHandlerRow;
	}

	public interface HttpCallBack {
		public void HttpGetData(String json);
	}

	public void setLogHandle(Handler logHandler){
		this.logHandler=logHandler;
	}
	
	
	// public void GetJeson(Handler handler,) {
	//
	// }
}
