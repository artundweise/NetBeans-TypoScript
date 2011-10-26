/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dfranek.typoscript;

import org.netbeans.modules.csl.spi.CommentHandler;

/**
 *
 * @author daniel
 */
public class TSCommentHandler extends CommentHandler.DefaultCommentHandler {

	private static final String COMMENT_START_DELIMITER = "/*"; //NOI18N
	private static final String COMMENT_END_DELIMITER = "*/"; //NOI18N


	
	@Override
	public String getCommentStartDelimiter() {
		return COMMENT_START_DELIMITER;
	}

	@Override
	public String getCommentEndDelimiter() {
		return COMMENT_END_DELIMITER;
	}
	
}
