package net.ssanj.port

import net.ssanj.port.rules._


object Rules extends SortLowerToUpperRule with
                     SortWithinGroupImportRule with
                     RemoveDuplicatesRule {

}