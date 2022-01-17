public class Input {
	public String input;
	public String[] storage = new String[100];
	public int counter = 0;

	public Input() {
	}

	public String input(String input) {
		this.input = input.toLowerCase();
		if (this.input.equals("list")) {
			for (int i = 0; i < counter; i++) {
				int numbering = i + 1;
			}
		} else {
			this.storage[counter] = this.input;
			this.counter++;
		}
		return this.input;
	}

	@Override
	public String toString() {
		return this.input;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj instanceof Input) {
			Input curr = (Input) obj;
			return this.input.equals(curr.input);
		} else {
			return false;
		}

	}
}
