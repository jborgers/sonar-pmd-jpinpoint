<!--+
    | Creates Sonar rules from a PMD ruleset
    +-->

<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        xmlns:functx="http://www.functx.com" xmlns:pmd="http://pmd.sourceforge.net/ruleset/2.0.0"
        exclude-result-prefixes="functx">
    <xsl:import href="https://raw.githubusercontent.com/transpect/xslt-util/master/functx/xsl/functx.xsl"/>

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
                    <internalKey>com/jpinpoint/pmd/rules/jpinpoint-rules-pmd7.xml/<xsl:value-of select="@name"/>
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
                        <xsl:variable name="description" select="./pmd:description/text()"/>
                        <xsl:variable name="problem" select="substring-before(substring-after($description, 'Problem:'), 'Solution:')"/>
                        <xsl:variable name="solution" select="functx:substring-before-if-contains(functx:substring-before-if-contains(
                            substring-after($description, 'Solution:'), 'Note:'), 'Exceptions:')"/>
                        <xsl:variable name="note" select="functx:substring-before-if-contains(substring-after($description, 'Note:'), 'Exceptions:')"/>
                        <xsl:variable name="exceptions" select="functx:substring-before-if-contains(substring-after($description, 'Exceptions:'), 'Note:')"/>
                        <xsl:variable name="example" select="replace(./pmd:example/text(), '^\s+|\s+$', '')"/>
                        <xsl:variable name="link-text" select="functx:substring-after-last(@externalInfoUrl,'/')"/>

                        <xsl:text disable-output-escaping="yes">&lt;![CDATA[</xsl:text>
                        <b><xsl:text>Problem:</xsl:text></b><xsl:value-of select="$problem"/><p/>
                        <b><xsl:text>Solution:</xsl:text></b><xsl:value-of select="$solution"/><p/>
                        <xsl:if test="$note != ''">
                            <b><xsl:text>Note:</xsl:text></b><xsl:value-of select="$note"/><p/>
                        </xsl:if>
                        <xsl:if test="$exceptions != ''">
                            <b><xsl:text>Exceptions:</xsl:text></b><xsl:value-of select="$exceptions"/><p/>
                        </xsl:if>
                        <b>Example:</b><code><pre><xsl:value-of select="$example"/></pre></code>
                        <b><xsl:text>More information: </xsl:text></b><a><xsl:attribute name="href"><xsl:value-of select="@externalInfoUrl"/></xsl:attribute><xsl:value-of select="$link-text"/></a><br/>
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
                        <xsl:if test="@name = 'tags'">
                            <xsl:for-each select="tokenize(@value, ',\s*')">
                                <tag><xsl:value-of select="."/></tag>
                            </xsl:for-each>
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
