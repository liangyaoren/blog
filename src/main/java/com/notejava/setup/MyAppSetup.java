package com.notejava.setup;

import com.notejava.refresh.Refresher;
import com.notejava.schedule.SyncBlog;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

import java.util.Date;
import java.util.Timer;

public class MyAppSetup implements Setup{

	@Override
	public void init(NutConfig nc) {
		Refresher.getInstance().refresh();
		new Timer().schedule(new SyncBlog(), new Date(), 1*60*60*1000);
	}

	@Override
	public void destroy(NutConfig nc) {}
}
