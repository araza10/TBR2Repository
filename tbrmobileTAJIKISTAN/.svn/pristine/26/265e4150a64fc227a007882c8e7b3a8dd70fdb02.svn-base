package org.irdresearch.tbreach.mobile.util;

import org.irdresearch.tbreach.mobile.model.MessageEntry;
import org.irdresearch.tbreach.mobile.ui.TBReachMainMIDlet;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.util.Hashtable;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;


public class HttpSender {
	
	private boolean currentWaitForResponse;
	public MessageEntry	entry;
	public String		baseUrl;
	public Hashtable	model;
	
	
	
	/*public TBReachMainMIDlet getTbrMidlet() {
		return tbrMidlet;
	}

	public void setTbrMidlet(TBReachMainMIDlet tbrMidlet) {
		this.tbrMidlet = tbrMidlet;
	}*/

	public Hashtable doPost(String baseUrl, MessageEntry entry) {
		HttpConnection hc = null;
		OutputStream os = null;
		String url = null;
		int responseCode;
		boolean successful = false;
		boolean waitForResponse;
		model = null;
		//XMLResponseModel responseModel = null;

		System.out.println("<doPost>");
		System.out.println("making URL");
		url = entry.getUrl();
		System.out.println("making URL");
		waitForResponse = entry.getWaitForResponse();
		currentWaitForResponse = waitForResponse;
		
		System.out.println("URL:" + url);
		/*System.out.println("try count:" + (submitRetries + 1));
		if (waitForResponse) {
			progressListener.update("URL try count " + (submitRetries + 1) + "\n" );
			progressListener.update("Connecting to " + url + "\n");
		} else {
			statusChangedListener.updateStatus("URL try count " + (submitRetries + 1) + "\n" );
			statusChangedListener.updateStatus("Connecting to " + url + "\n");			
		}
		if (waitForResponse) {
			progressListener.update("Params :" + entry.getPostParams() + "\n");
		} else {
			statusChangedListener.updateStatus("Params :" + entry.getPostParams() + "\n");			
		}*/
		
		System.out.println("WaitForResponse = " + String.valueOf(waitForResponse));
		System.out.println("Connecting");
		try {
			//FileWriter.rite("Post Paramters :" + entry.getPostParams(), tbrMidlet);
			hc = (HttpConnection) Connector.open(url, Connector.READ_WRITE, true);
			hc.setRequestMethod(HttpConnection.POST);
			hc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			hc.setRequestProperty("User-Agent", "Profile/MIDP-2.0 Configuration/CLDC-1.0");
			hc.setRequestProperty("Content-Language", "en-US");

//			currentConnection = hc;
//			System.out.println("Monitor Task scheduled");
//			initTimer();
			
			System.out.println("Post Paramters :" + entry.getPostParams());
			//tbrMidlet.showAlert("Post Paramters :" + entry.getPostParams(), null);
			
			os = hc.openOutputStream();
			os.write(entry.getPostParams().getBytes("UTF-8"));
			os.flush();
			//tbrMidlet.showAlert("here", null);
			//FileWriter.rite("here", tbrMidlet);
			
			/*if (waitForResponse) {
				progressListener.update("trying to post...\n");
			} else {
				statusChangedListener.updateStatus("trying to post...\n");
			}*/

			
			responseCode = hc.getResponseCode();
			if (responseCode != HttpConnection.HTTP_OK) {
				//tbrMidlet.showAlert(new Integer(responseCode).toString(), null);
				//FileWriter.rite(new Integer(responseCode).toString(), tbrMidlet);
				throw new IOException("Http response code: " + responseCode);
			}
			
			if (waitForResponse) {
			//	progressListener.update("Connection successful\n");
			//	progressListener.update("Parsing response...\n");

				System.out.println("Parsing response");
			//	tbrMidlet.showAlert("Parsing response", null);
				/*InputStreamReader is = new InputStreamReader(hc.openInputStream());
				System.out.println("Response:" + hc.getResponseMessage());
				int check;
				is.
				while((check=is.read())!='\0') {
					System.out.print(check);
				}
				*/
				model = XmlUtil.parseXmlResponse(new InputStreamReader(hc.openInputStream()));
				//System.out.println(responseModel);				
				//progressListener.update(responseModel.toString() + "\n");
				//progressListener.update("Response read successfully\n");
				System.out.println("Response Complete");
				successful = true; // the progress listener handles if this is null
			} else {
				//statusChangedListener.updateStatus("Connection successful\n");
				//statusChangedListener.updateStatus("Parsing response...\n");

				System.out.println("Parsing response");
				
				/*responseModel = XMLUtil.parseXMLResponse(new InputStreamReader(hc.openInputStream()));
				if (responseModel != null) {
					successful = true;*/
				}
				//System.out.println(responseModel);				
				//statusChangedListener.updateStatus(responseModel.toString() + "\n");
				//statusChangedListener.updateStatus("Response read successfully\n");
				System.out.println("Response Complete");
				
			//}

		} catch (ClassCastException e) {
			throw new IllegalArgumentException("Not an HTTP URL");
		} catch (IOException e) {
			/*if (waitForResponse) {
				progressListener.update("Connection error: " + e.toString() + "\n");
			} else {
				statusChangedListener.updateStatus("Connection error: " + e.toString() + "\n");
			}*/
			e.printStackTrace();
		} catch (SecurityException e) {
			/*if (waitForResponse) {
				progressListener.update("Permission not granted: " + e.toString() + "\n");
			} else {
				statusChangedListener.updateStatus("Permission not granted: " + e.toString() + "\n");
			}*/
			e.printStackTrace();					
		} //catch (XmlPullParserException e) {
			//progressListener.update("XML parsing failed: " + e.toString() + "\n");
			//e.printStackTrace();
		/*}*//* catch (TimeoutException e) {
			if (waitForResponse) {
				progressListener.update("Took too long: " + e.toString() + "\n");
			} else {
				statusChangedListener.updateStatus("Took too long: " + e.toString() + "\n");
			}		
		}*/ catch (Exception e)  {
			if (e instanceof InterruptedException) {				
				/*if (waitForResponse) {
					progressListener.update("Took too long: " + e.toString() + "\n");
				} else {
					statusChangedListener.updateStatus("Took too long: " + e.toString() + "\n");
				}
			} else {
				if (waitForResponse) {
					progressListener.update("Unknown error: " + e.toString() + "\n");
				} else {
					statusChangedListener.updateStatus("Unknown error: " + e.toString() + "\n");
				}*/				
			}
			e.printStackTrace();			
		} finally {
//			currentConnection = null;
//			monitor.cancel();
//			if (waitForResponse) {
//				progressListener.update("Timer task cancelled \n");
//			} else {
//				statusChangedListener.updateStatus("Timer task cancelled \n");
//			}
//			System.out.println("Monitor Task Cancelled");

			if (os != null) {
				try {
					System.out.println("Closing output stream");
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			
			if (hc != null) {
				try {
					System.out.println("Closing http connection");
					hc.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			/*if (waitForResponse) {
				if (successful) {
					submitRetries = 0;
				} else {
					submitRetries++;
				}
				progressListener.stop(successful, responseModel);				
			} else {
				if (successful) {
					statusChangedListener.updateStatus("Success, message posted");			
					submitRetries = 0;
				} else {
					statusChangedListener.updateStatus("\nFailure, queuing it again for retry..");
	
					addToQueue(entry, RETRY_DELAY);
					System.out.println("re queing");
					submitRetries++;
				}				
			}*/
			System.out.println("</doPost>");
			
		}	
		return model;
	}

	private void doGet(String baseUrl, MessageEntry entry) {
		HttpConnection hc = null;
		String url = null;
		int responseCode;
		boolean successful = true;
		//XMLResponseModel responseModel = null;
		
		System.out.println("<doGet>");
		url = baseUrl + "/" + entry.getUrl();
		//progressListener.update("URL try count " + (submitRetries + 1) + "\n" );
		//progressListener.update("Connecting to " + url + "\n");

		//progressListener.update("Params :" + entry.getPostParams() + "\n");
		currentWaitForResponse = true;

		System.out.println("Connecting");
		try {
			hc = (HttpConnection) Connector.open(url);
			hc.setRequestMethod(HttpConnection.GET);
			
//			currentConnection = hc;
//			System.out.println("Monitor Task scheduled");
//			initTimer();

			responseCode = hc.getResponseCode();
			if (responseCode != HttpConnection.HTTP_OK) {
				successful = false;
				throw new IOException("Http response code: " + responseCode);
			}
			//progressListener.update("Connection successful\n");
			System.out.println("Parsing response");

			//progressListener.update("Parsing response...\n");
			//responseModel = XMLUtil.parseXMLResponse(new InputStreamReader(hc.openInputStream()));
			//System.out.println(responseModel);
			
		} catch (ClassCastException e) {
			successful = false;
			throw new IllegalArgumentException("Not an HTTP URL");
		} catch (IOException e) {
			successful = false;
			//progressListener.update("Connection error:" + e.toString() + "\n");
			e.printStackTrace();
		} catch (SecurityException e) {
			successful = false;
			//progressListener.update("Permission not granted:" + e.toString()+ "\n");
			e.printStackTrace();					
		} /*catch (XmlPullParserException e) {
			successful = false;
			//progressListener.update("XML parsing failed:" + e.toString() + "\n");
			e.printStackTrace();
		}*/catch (Exception e)  {
			successful = false;
			if (e instanceof InterruptedException) {				
				//progressListener.update("Took too long: " + e.toString() + "\n");
			} else {
				//progressListener.update("Unknown error: " + e.toString() + "\n");
			}
			e.printStackTrace();			
		} finally {
//			currentConnection = null;
//			monitor.cancel();
//			System.out.println("Monitor Task Cancelled");

			if (!successful) {
				//submitRetries++;
			} else {
				//submitRetries = 0;
			}
			
			//progressListener.stop(successful, responseModel);
			if (hc != null) {
				try {
					System.out.println("Closing http connection");
					hc.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("</doGet>");
		}								
	}
}
