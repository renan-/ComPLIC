package complic.analyse.expressions.binaires;

import complic.analyse.ASMUtils;
import complic.analyse.Expression;
import complic.analyse.expressions.Binaire;

import complic.definitions.Types;

import complic.erreurs.ErreurSemantique;

/**
 * @author Renan Strauss
 *
*/
public class Ou extends Binaire {
	public Ou(Expression fg, Expression fd) {
		super(fg, fd);
	}

	@Override
	public String getType() throws ErreurSemantique {
		if(!(this.filsGauche.getType().equals(Types.BOOLEEN)
		  && this.filsDroit.getType().equals(Types.BOOLEEN))) {
			throw new ErreurSemantique("'ou' expected type 'booleen'");
		}
		return Types.BOOLEEN;
	}

	@Override
	public String genererOperation() {
		return ASMUtils.getInstance().genererOu();
	}
}