package ni.vsbuild

class PipelineStatus implements Serializable {

   public static PipelineResult getResult(def script) {
      // Modified from https://jenkins.io/doc/pipeline/tour/running-multiple-steps/#finishing-up
      def currentResult = script.currentBuild.result ?: 'SUCCESS'
      def previousResult = script.currentBuild.previousBuild?.result
      def currentFailure = (currentResult == 'FAILURE')

      if (currentResult == 'UNSTABLE') {
         return PipelineResult.UNSTABLE
      }

      if (previousResult) {
         PipelineResult result

         if (currentResult == previousResult) {
            // Build is still good or still broken
            result = currentFailure ? PipelineResult.EXISTING_FAILURE : PipelineResult.SUCCESS
         }
         else {
            // Build is newly fixed or newly broken
            result = currentFailure ? PipelineResult.NEW_FAILURE : PipelineResult.FIXED
         }

         return result
      }

      // First build is either good or new failure
      return currentFailure ? PipelineResult.NEW_FAILURE : PipelineResult.SUCCESS
   }
}
