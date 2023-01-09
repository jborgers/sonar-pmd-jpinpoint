<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:pmd="http://pmd.sourceforge.net/ruleset/2.0.0">
    <xsl:output method="text"/>
    <xsl:template match="/">
        <xsl:for-each select="/pmd:ruleset/pmd:rule">
* **<xsl:value-of select="@name"/>**: <xsl:value-of select="@message"/>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>