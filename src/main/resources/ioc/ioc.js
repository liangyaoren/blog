var ioc = {
		// 读取配置文件
	    config : {
	        type : "org.nutz.ioc.impl.PropertiesProxy",
	        fields : {
	            paths : ["args.properties"]
	        }
	    },
	    
	 // 数据源
	    dataSource : {
	        type :"com.alibaba.druid.pool.DruidDataSource",
	        events : {
	        	create : "init",
	            depose :"close"
	        },
	        fields : {
	        	url : {java:"$config.get('db.url')"},
                username : {java:"$config.get('db.username')"},
                password : {java:"$config.get('db.password')"},
                testWhileIdle : true,
                validationQuery : {java:"$config.get('db.validationQuery')"},
                maxActive : {java:"$config.get('db.maxActive')"},
                filters : "wall,mergeStat",
                connectionProperties : "druid.stat.slowSqlMillis=2000"
	        }
	    },
	    
	    dao : {
            type : "org.nutz.dao.impl.NutDao",
            args : [{refer:"dataSource"}]
        },
        
        moduleFiter : {
        	type : "com.notejava.filters.ModuleFilter",
        },

        loginInterceptor: {
	        type : "com.notejava.filters.LoginInterceptor"
        },
        
        $aop : {
            type : 'org.nutz.ioc.aop.config.impl.JsonAopConfigration',
            fields : {
                itemList : [
                    ['.+Module','.+[^_]$','ioc:moduleFiter'],
                    ['.+AdminModule','.+[^_]$','ioc:loginInterceptor']
                ]
            }
        },
        
        tmpFilePool : {
            type : 'org.nutz.filepool.NutFilePool',
            // 临时文件最大个数为 1000 个
            args : [ "~/tmps", 1000 ]
        },
        uploadFileContext : {
            type : 'org.nutz.mvc.upload.UploadingContext',
            singleton : false,
            args : [ { refer : 'tmpFilePool' } ],
            fields : {
                // 是否忽略空文件, 默认为 false
                ignoreNull : true,
                // 单个文件最大尺寸(大约的值，单位为字节，即 1048576 为 1M)
                maxFileSize : 10485760
                // 正则表达式匹配可以支持的文件名
                //,nameFilter : '^(.+[.])(gif|jpg|png)$' 
            } 
        },
        fileUpload : {
            type : 'org.nutz.mvc.upload.UploadAdaptor',
            singleton : false,
            args : [ { refer : 'uploadFileContext' } ] 
        },
}