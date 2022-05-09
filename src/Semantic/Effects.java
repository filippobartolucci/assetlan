package Semantic;

public enum Effects {
	BOTTOM,
	RW,
	D,
	TOP;

	public Effects Max(Effects e){
		return this.ordinal() > e.ordinal() ? this : e;
	}

	public Effects Seq(Effects e){
		Effects max = this.Max(e);

		// if max(a,b) <= rw --> return max(a,b)
		if (max.ordinal() <= RW.ordinal() ) {return max;}

		// if (a≤rw and b=d) or (a=d and b=⊥) --> return d
		if ( (this.ordinal() <= RW.ordinal() && e == D) || (this == D && e ==  BOTTOM ) ){return D;}

		// T otherwise
		return TOP;
	}

	public Effects Inc(){
		switch (this) {
			case BOTTOM:
				return RW;
			case RW:
				return D;
			case D:
				return TOP;
		}
		return TOP;
	}
}

