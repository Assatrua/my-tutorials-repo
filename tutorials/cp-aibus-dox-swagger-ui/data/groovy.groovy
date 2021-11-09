def DRY_RUN = true


def GROUP_RESOURCE_TYPE = 'developers/components/page/groupPage'
def MISSION_PAGE_NAME = 'mission'
def GROUP_PAGE_NAME = 'group'
String [] SEMAPHORE_IDS = ['9b39e112-292a-4b14-a747-b4fff6b2eec1']
def affectedNodes = []

if (DRY_RUN) {
    println 'Script is executing in dry run mode, no changes will be made to repository'
}

try {
    addTutorialSemaforeTagId(MISSION_PAGE_NAME, affectedNodes, SEMAPHORE_IDS)
    addTutorialSemaforeTagId(GROUP_PAGE_NAME, affectedNodes, SEMAPHORE_IDS)
} catch (RepositoryException e) {
    log.error("Could not perform operation.", e.getMessage())
    println "Could not perform operation. See logs for details."
}

def addTutorialSemaforeTagId(pageName, affectedNodes, semaphoreIds){
    def query = "SELECT * FROM [nt:unstructured] WHERE ISDESCENDANTNODE('/content/developers/website/languages/en/${pageName}') AND [sling:resourceType] = 'developers/components/page/groupPage'"
    executeSQL2(query).each{
        node ->
            if (session.itemExists(node.path)) {
                node.setProperty('cq:tags', semaphoreIds)
                println "Set  property cq:tags with '${semaphoreIds}' value to node '${node.path}'"
                affectedNodes.add(node)
            }
    }
    
}

printAffectedItems(affectedNodes)
println saveSession(DRY_RUN)