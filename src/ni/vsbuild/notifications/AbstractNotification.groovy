package ni.vsbuild.notifications

abstract class AbstractNotification implements Notification {

   def script
   def type

   AbstractNotification(script, notificationInfo) {
      this.script = script
      this.type = notificationInfo.get('type')
   }

   void notify(message) {
      sendNotification(message)
   }

   abstract void sendNotification(message)

}
