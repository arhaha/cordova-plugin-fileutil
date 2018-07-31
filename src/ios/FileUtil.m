/********* FileUtil.m Cordova Plugin Implementation *******/

#import "FileUtil.h"

@implementation FileUtil

- (void)readFileAsBytes:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    NSString* filePath = [command.arguments objectAtIndex:0];

    if (filePath != nil && [filePath length] > 0) {
        NSData *resultData = [FileUtil readNSDataFromFile:filePath];
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsArrayBuffer:resultData];
    } else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
    }

    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

+ (NSData *)readNSDataFromFile:(NSString*)filePath
{
    filePath = [FileUtil formatFilePath:filePath];
    NSData *resultData = [NSData dataWithContentsOfFile:filePath];
    NSLog(@"readNSDataFromFile filePath:%@:  Data length:%lu", filePath, [resultData length]);
    return resultData;
}

+(NSString *)formatFilePath:(NSString*)filePath
{
    if ([filePath hasPrefix:@"file://"]) {
        filePath = [filePath substringFromIndex: 7];
    }
    return filePath;
}

@end
