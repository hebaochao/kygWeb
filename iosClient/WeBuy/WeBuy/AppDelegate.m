//
//  AppDelegate.m
//  WeBuy
//
//  Created by Alex on 15/10/21.
//  Copyright (c) 2015年 Alex. All rights reserved.
//

#import "AppDelegate.h"
#import "WBMainViewController.h"
#import "SDWebImageManager.h"
#import <AVFoundation/AVFoundation.h>
#import "Reachability.h"
#import "WBAccount.h"
#import <TencentOpenAPI/TencentOAuth.h>
@interface AppDelegate ()

//@property(nonatomic,strong)AVAudioPlayer *player;
//@property(nonatomic,strong)NSTimer  *timer;

@end

@implementation AppDelegate

#pragma mark --程序运行起来时调用此方法
- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {
  
    
    //初始化相关监听通知
    [self initNotifications:application];
//    //初始化音乐播放器
//    [self playMusic];
    
    
    //创建窗体
    UIWindow *window=[[UIWindow alloc]initWithFrame:[UIScreen mainScreen].bounds];
    //创建控制器
    WBMainViewController *mainVC=[[WBMainViewController alloc]init];
    //设置控制器为window的根控制器
    window.rootViewController=mainVC;
    
    //设置窗体到应用中
    self.window=window;
    //让窗体成为主主窗体并显示
    [self.window makeKeyAndVisible];
    
    
    
   
    //向腾讯注册QQ分享
    [[TencentOAuth alloc] initWithAppId:qq_appid andDelegate:nil];
    
    
    
    return YES;
}



#pragma mark ---初始化监听相关通知
-(void)initNotifications:(UIApplication *)application {
    //为application注册通知 UIUserNotificationTypeBadge才能在图标上显示数字
    UIUserNotificationSettings *setting=[UIUserNotificationSettings settingsForTypes:UIUserNotificationTypeBadge categories:nil];
    [application registerUserNotificationSettings:setting];
    
    
//为application添加监听网络状态通知
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(networkChange:) name:kReachabilityChangedNotification object:nil];
        Reachability *reach=[Reachability reachabilityWithHostName:@"www.baidu.com"];
   
    
    [reach startNotifier];
 
    
    
    //创建并开启定时器
//    NSTimer *timer=[NSTimer scheduledTimerWithTimeInterval:1 target:self selector:@selector(checkNetWordStatus)  userInfo:nil repeats:YES];
//    //获取主运行循环并把定时器放置到主运行循环中
//    NSRunLoop *loop=[NSRunLoop mainRunLoop];
//    [loop addTimer:timer forMode:NSDefaultRunLoopMode];
//    _timer=timer;
  
}

#pragma mark ---定时器 检测网络状态
-(void)checkNetWordStatus{
     Reachability *reach=[Reachability reachabilityWithHostName:@"www.baidu.com"];
         [reach startNotifier];
}



#pragma mark ---网络状态变化处理
/**
 *      NotReachable = 0,
 ReachableViaWiFi = 2,
 ReachableViaWWAN = 1
 */
-(void)networkChange:(NSNotification *)note{
    Reachability *reach = [note object];
    
    if([reach isKindOfClass:[Reachability class]]){
        
        NetworkStatus status = [reach currentReachabilityStatus];
        
        //NSLog(@"%ld",status);
        if (status==NotReachable) {//保存当前网络状态到当前全局变量中
               [WBAccount shareAccount].network=NO;
        }else{
              [WBAccount shareAccount].network=YES;
        }
     
        
    }
}




#pragma mark ---初始化音乐播放器
//-(void)playMusic{
//    //真机上需要播放音乐 1、 需要设置session和激活session
//    AVAudioSession *session=[ AVAudioSession sharedInstance];
//    //2、设置播放类型
//    [session setCategory:AVAudioSessionCategoryPlayback error:nil];
//    //3、激活session
//    [session setActive:YES error:nil];
//   
//}



#pragma mark ---内存警告时处理方法

/**
 *  接收到内存警告时调用此方法
 释放一些资源 保证程序能正常运行
 *使用SDwebimage很容易内存溢出
 *  @param application <#application description#>
 */
-(void)applicationDidReceiveMemoryWarning:(UIApplication *)application{
    //取消当前的缓存任务
    [[SDWebImageManager sharedManager] cancelAll];
    //清空当前内存中的缓存
    [[SDWebImageManager sharedManager].imageCache clearMemory];
}



#pragma mark ---控制器将失去焦点时调用此方法  也就是准备进入后台运行时
/**
 *  控制器（界面）失去焦点时调用
 *
 *  @param application <#application description#>
 */
//- (void)applicationWillResignActive:(UIApplication *)application {
//    // NSLog(@"%s",__func__);
//    
//    
//    NSURL *fileurl=[[NSBundle mainBundle] URLForResource:@"silence.mp3" withExtension:nil];
//    
//    AVAudioPlayer *player=[[AVAudioPlayer alloc]initWithContentsOfURL:fileurl error:nil];
//    //缓存文件到播放器中
//    [player prepareToPlay];
//    //无限循环播放
//    player.numberOfLoops=-1;
//    //播放音乐
//    [player play];
//    self.player=player;
//}




#pragma mark ---程序在后台中处理

//后台运行

//- (void)applicationDidEnterBackground:(UIApplication *)application {
//    //开启应用后台线程 用来运行应用
//    __weak typeof(self) myself;
//    UIBackgroundTaskIdentifier ID=[application beginBackgroundTaskWithExpirationHandler:^{
//        
//        [myself.player play];
//        //停止后台线程任务
//        [application endBackgroundTask:ID];
//    }];
//    
//
//    //提高优先级  不轻易让苹果系统关系后台应用程序
//    //因此开始后台运行功能 播放静音的音乐 欺骗苹果系统
//    
//}



#pragma marl ---程序从后台再次进入前台处理
//再次从后台进入后调用此方法
//
//- (void)applicationDidBecomeActive:(UIApplication *)application {
//
//    //停止音乐播放
//    [self.player play];
//    self.player=nil;
//    // NSLog(@"%s",__func__);
//}
//
//-(void)dealloc{
////移除所有通知
//    [[NSNotificationCenter defaultCenter] removeObserver:self];
//    
//    //停止计时器
//    [self.timer invalidate];
//    self.timer=nil;
//}


#pragma mark --腾讯框架需要重写方法
-(BOOL)application:(UIApplication *)application openURL:(NSURL *)url sourceApplication:(NSString *)sourceApplication annotation:(id)annotation
{
    return [TencentOAuth HandleOpenURL:url];
}




@end
