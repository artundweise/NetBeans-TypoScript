/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2011 Daniel Franek.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 * 
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 * 
 */
package net.dfranek.typoscript.parser;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ChangeListener;
import net.dfranek.typoscript.lexer.TSTokenId;
import org.netbeans.api.lexer.TokenSequence;
import org.netbeans.modules.csl.spi.ParserResult;
import org.netbeans.modules.parsing.api.Snapshot;
import org.netbeans.modules.parsing.api.Task;
import org.netbeans.modules.parsing.spi.ParseException;
import org.netbeans.modules.parsing.spi.Parser;
import org.netbeans.modules.parsing.spi.SourceModificationEvent;

/**
 *
 * @author Daniel Franek
 */
public class TSParser extends Parser {

	private Snapshot snapshot;
	private ParserResult result = null;
	private static final Logger LOGGER = Logger.getLogger(TSParser.class.getName());

	@Override
	public void parse(Snapshot snapshot, Task task, SourceModificationEvent sme) throws ParseException {
		this.snapshot = snapshot;
		//File split into tokens
		TokenSequence<TSTokenId> sequence = snapshot.getTokenHierarchy().tokenSequence(TSTokenId.getLanguage());
		try {
			result = parseSource(sequence);
		} catch (Exception e) {
			LOGGER.log(Level.FINE, "Exception during parsing: {0}", e);
			result = new TSParserResult(snapshot);
		}
	}

	@Override
	public Result getResult(Task task) throws ParseException {
		return result;
	}

	@Override
	public void addChangeListener(ChangeListener cl) {
	}

	@Override
	public void removeChangeListener(ChangeListener cl) {
	}

	private ParserResult parseSource(TokenSequence<TSTokenId> source) {
		ParserResult pResult;
		TSTokenParser p = new TSTokenParser(source, snapshot);
		pResult = p.analyze();

		return pResult;
	}
}
