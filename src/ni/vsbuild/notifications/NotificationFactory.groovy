package ni.vsbuild.notifications

class NotificationFactory implements Serializable {

   static Notification createNotification(script, notificationInfo) {
      def type = notificationInfo.get('type')

      if(type == 'email') {
         return new Email(script, notificationInfo)
      }

      script.failBuild("\'$type\' is an invalid notification type.")
   }
}
