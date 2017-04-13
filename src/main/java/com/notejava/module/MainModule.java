package com.notejava.module;

import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

import com.notejava.setup.MyAppSetup;

@Modules(scanPackage = true)
@SetupBy(value=MyAppSetup.class)
@IocBy(type=ComboIocProvider.class, args={"*js", "ioc/","*anno", "com.notejava","*async","*tx"})
public class MainModule {

}
