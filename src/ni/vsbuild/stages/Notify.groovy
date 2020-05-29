package ni.vsbuild.stages

import ni.vsbuild.notifications.Notification
import ni.vsbuild.notifications.NotificationFactory

class Notify extends AbstractStage {

   def notificationType

   Notify(script, configuration, notificationType) {
      super(script, 'Notify', configuration, null)
      this.notificationType = notificationType
   }

   void executeStage() {
      def notificationInfoCollection = []

      // Developers can specify a single notification [Notification]
      // or a collection of notifications [[Notification]].
      // Test the notification information parameter and iterate as needed.
      if (configuration.notificationInfo in Collection) {
         notificationInfoCollection = configuration.notificationInfo
      }
      else {
         notificationInfoCollection.add(configuration.notificationInfo)
      }

      for (def notificationInfo : notificationInfoCollection) {
         Notification notification = NotificationFactory.createNotification(script, notificationInfo)
         notification.sendNotification(notificationType.message)
      }
   }
}
