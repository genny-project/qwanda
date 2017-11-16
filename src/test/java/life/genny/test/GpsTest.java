package life.genny.test;

import org.junit.Test;

import com.google.gson.Gson;

import life.genny.qwanda.GPS;
import life.genny.qwanda.message.QDataGPSMessage;
import life.genny.test.qwanda.util.JsonUtils;

public class GpsTest {
	@Test
	public void gpsTest() {
		// {"coords":{"speed":-1,"longitude":151.211,"latitude":-33.8634,"accuracy":5,"heading":-1,"altitude":0,"altitudeAccuracy":-1},"timestamp":1510809896963.5588}

		GPS[] items = new GPS[2];
		String speed = "-1";
		String longitude = "151.211";
		String latitude = "-33.8634";
		String accuracy = "5";
		String heading = "-1";
		String altitude = "0";
		String altitudeAccuracy = "-1";
		String timestamp = "1510809896963.5588";

		GPS gps = new GPS("PER_USER1", latitude, longitude);
		items[0] = gps;

		QDataGPSMessage msg = new QDataGPSMessage(items);
		JsonGenerator(msg);

	}

	private void JsonGenerator(final Object src) {

		new Gson();
		try {
			final String json = JsonUtils.set(src); // gson.toJson(src);
			System.out.println("*** " + src.getClass().getSimpleName());
			System.out.println(json);
		} catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
