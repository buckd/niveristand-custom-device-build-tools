package ni.vsbuild.notifications

import ni.vsbuild.PipelineResult

public class NotificationMessage extends Serializable {

   def script
   private final PipelineResult result

   public NotificationMessage(def script, PipelineResult result) {
      this.script = script
      this.result = result
   }

   public String getHeader() {
      def header = "Build ${script.env.JOB_NAME} ${result.asString()}."
      return header
   }

   public String getMessage() {
      def message = """
         Build ${script.env.BUILD_ID} of ${script.env.JOB_NAME} finished with a result of ${result.name()}.
         ${script.env.BUILD_URL}
         """.stripIndent()
   }
}
