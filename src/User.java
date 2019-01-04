
public class User { //유저 클래스
        private String id; //아이디
        private String pwd; //비밀번호
		//생성자
        public User(String id, String pwd) {
			super();
			this.id = id;
			this.pwd = pwd;
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", pwd=" + pwd + "]";
		}
       // setter 및 getter
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getPwd() {
			return pwd;
		}

		public void setPwd(String pwd) {
			this.pwd = pwd;
		}
        
        
        
}
