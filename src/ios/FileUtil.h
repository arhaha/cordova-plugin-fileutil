#import <Cordova/CDV.h>

@interface FileUtil : CDVPlugin {
  // Member variables go here.
}

- (void)readFileAsBytes:(CDVInvokedUrlCommand*)command;
@end