import Exceptions.BadSyntaxException;

public class Join extends Select{
    
    public Join(String query) {
        super(query);
    }

    public void joinTables() throws BadSyntaxException {
        if(!this.content.get(this.content.size()-2).equals("on") || !this.content.get(this.content.size()-4).equals("join")){
            throw new BadSyntaxException("Bad syntax", "join on", this.content.get(this.content.size()-4) + " " + this.content.get(this.content.size()-2));
        }
    }
}

