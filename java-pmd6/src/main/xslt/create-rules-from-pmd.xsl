<!--+
    | Creates Sonar rules from a PMD ruleset
    +-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:pmd="http://pmd.sourceforge.net/ruleset/2.0.0">
    <xsl:output method="xml" omit-xml-declaration="yes" indent="yes"/>
    <xsl:template match="/">
        <rules>
            <xsl:for-each select="/pmd:ruleset/pmd:rule">
                <rule>
                    <key>
                        <xsl:value-of select="@name"/>
                    </key>
                    <name>
                        <xsl:value-of select="@message"/>
                    </name>
                    <internalKey>com/jpinpoint/pmd/rules/pmd-custom-java.xml/<xsl:value-of select="@name"/>
                    </internalKey>
                    <severity>
                        <xsl:choose>
                            <xsl:when test="./pmd:priority/text() = 1">BLOCKER</xsl:when>
                            <xsl:when test="./pmd:priority/text() = 2">CRITICAL</xsl:when>
                            <xsl:when test="./pmd:priority/text() = 3">MAJOR</xsl:when>
                            <xsl:when test="./pmd:priority/text() = 4">MINOR</xsl:when>
                            <xsl:when test="./pmd:priority/text() = 5">INFO</xsl:when>
                            <xsl:otherwise>INFO</xsl:otherwise>
                        </xsl:choose>
                    </severity>
                    <description>
                        <xsl:text disable-output-escaping="yes">&lt;![CDATA[</xsl:text>
                        <xsl:value-of select="./pmd:description/text()"/><p/>
                        <xsl:text>More information: </xsl:text><a><xsl:attribute name="href"><xsl:value-of select="@externalInfoUrl"/></xsl:attribute>Documentation</a>
                        <xsl:text disable-output-escaping="yes">]]&gt;</xsl:text>
                    </description>
                    <xsl:for-each select="./pmd:properties/pmd:property">
                        <xsl:if test="@name = 'status'">
                            <status><xsl:value-of select="@value"/></status>
                        </xsl:if>
                        <xsl:if test="@name = 'type'">
                            <type><xsl:value-of select="@value"/></type>
                        </xsl:if>
                        <xsl:if test="@name = 'tag'">
                            <tag><xsl:value-of select="@value"/></tag>
                        </xsl:if>
                        <xsl:if test="@description">
                            <xsl:if test="starts-with(@name, 'param-')">
                                <param>
                                    <key><xsl:value-of select="@name"/></key>
                                    <description>
                                        <xsl:text disable-output-escaping="yes">&lt;![CDATA[</xsl:text>
                                        <xsl:value-of select="@description"/>
                                        <xsl:text disable-output-escaping="yes">]]&gt;</xsl:text>
                                    </description>
                                    <defaultValue><xsl:value-of select="@value"/></defaultValue>
                                </param>
                            </xsl:if>
                        </xsl:if>
                    </xsl:for-each>
                </rule>
            </xsl:for-each>
        </rules>
    </xsl:template>
</xsl:stylesheet>
