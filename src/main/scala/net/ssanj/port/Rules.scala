package net.ssanj.port

import net.ssanj.port.rules.{SortLowerToUpperRule, SortWithinGroupImportRule}


object Rules extends SortLowerToUpperRule with
                     SortWithinGroupImportRule {

}