package complic.analyse.instructions;

import complic.analyse.ASMUtils;
import complic.analyse.Expression;
import complic.analyse.Instruction;
import complic.analyse.TableSymboles;

import complic.erreurs.ErreurSemantique;

/**
 * Du style
 * lhs := rhs ;
*/
public class Affectation implements Instruction {
	private String lhs;
	private Expression rhs;

	public Affectation(String lhs, Expression rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}

	@Override
	public String generer() {
		int ptr = TableSymboles.getInstance().get(this.lhs).getAddr();
		return ASMUtils.getInstance().genererAffectation(rhs, ptr);
	}

	@Override
	public void verifier() {
		if(!TableSymboles.getInstance().hasKey(this.lhs)) {
			throw new ErreurSemantique("Unknown symbol : '" + this.lhs + "'");
		}

		/**
		 * On verifie la compatibilite des types
		*/
		String lhsType = this.getType(this.lhs);
		String rhsType = this.rhs.getType();
		if(!lhsType.equals(rhsType)) {
			throw new ErreurSemantique("Incompatible types : '" + lhsType + "' and '" + rhsType + "'");
		}
	}

	private String getType(String idf) {
		return TableSymboles.getInstance().get(idf).getType();
	}

	@Override
	public String toString() {
		return this.lhs + " = " + this.rhs + "\n";
	}
}