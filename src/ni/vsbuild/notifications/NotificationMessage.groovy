package ni.vsbuild.notifications

public enum NotificationMessage {
   SUCCESS('succeeded'),
   FIXED('fixed'),
   NEW_FAILURE('failed'),
   EXISTING_FAILURE('still failing'),
   UNSTABLE('unstable')

   private final String value
   
   NotificationMessage(String value) {
      this.value = value
   }
   
   public Map getMessage() {
      def header = "Build ${env.JOB_NAME} $value."
      return [header: header, body: getBody()]
   }

   private String getBody() {
      def body = """
      Build ${env.BUILD_ID} of ${env.JOB_NAME} finished with a status of ${name()}.
      ${env.BUILD_URL}
      """.stripIndent()
   }
}
