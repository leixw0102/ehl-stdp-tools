package com.ehl.yarn.base;

import org.apache.hadoop.yarn.applications.distributedshell.ApplicationMaster;
import org.apache.hadoop.yarn.client.api.YarnClient;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.hadoop.yarn.server.resourcemanager.ResourceManager;

/**
 * Created by 雷晓武 on 2017/5/24.
 * Following are the important interfaces:

 Client<-->ResourceManager

 By using YarnClient objects.

 ApplicationMaster<-->ResourceManager

 By using AMRMClientAsync objects, handling events asynchronously by AMRMClientAsync.CallbackHandler

 ApplicationMaster<-->NodeManager

 Launch containers. Communicate with NodeManagers by using NMClientAsync objects, handling container events by NMClientAsync.CallbackHandler
 */
public class YarnExample1 {

    public static void main(String[]args){
        YarnClient yarnClient = YarnClient.createYarnClient();
        yarnClient.init(new YarnConfiguration());
        yarnClient.start();
//        ResourceManager
        ApplicationMaster a=null;
    }

}
