
public class User { //���� Ŭ����
        private String id; //���̵�
        private String pwd; //��й�ȣ
		//������
        public User(String id, String pwd) {
			super();
			this.id = id;
			this.pwd = pwd;
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", pwd=" + pwd + "]";
		}
       // setter �� getter
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
