from flask import Flask, render_template, request
import sqlite3 as sql
app = Flask(__name__)

@app.route('/')
def home():
   return render_template('home.html')

@app.route('/student')
def student_log():
   return render_template('student.html')

@app.route('/student-apply')
def apply():
	return render_template('student-apply.html');


@app.route('/addrecord',methods=['GET','POST'])
def addrec():
	if request.method == 'POST':
		try:
			name=request.form['name']
			id = request.form['id']
			pointer = request.form['ptr']
			company = request.form['company']
         
			with sql.connect("database.db") as con:
				cur = con.cursor()
            
				cur.execute("INSERT INTO students (name,id,pointer,company,status) VALUES (?,?,?,?,?)",(name,id,pointer,company,"applied") )
            
				con.commit()
				msg = "Record successfully added"
		except:
			con.rollback()
			msg = "error in insert operation"
      
		finally:
			return render_template("result.html",msg = msg)
			con.close()

@app.route('/knowstatus')
def status():
	return render_template('knowstatus.html');
	
	
@app.route('/student-appstatus',methods=['GET','POST'])
def list():
	if request.method == 'POST':
		id = request.form['id']
		con = sql.connect("database.db")
		con.row_factory = sql.Row
   
		cur = con.cursor()
		cur.execute("select * from students where id=?",[id])
   
		rows = cur.fetchall();
		return render_template("student-appstatus.html",rows = rows)

@app.route('/tpo')
def tpo_log():
	return render_template('tpo.html');
	
@app.route('/view-app')
def view():
	con = sql.connect("database.db")
	con.row_factory = sql.Row
   
	cur = con.cursor()
	cur.execute("select * from students ")
   
	rows = cur.fetchall();
	return render_template("student-appstatus.html",rows = rows)

@app.route('/update-status',methods=['GET','POST'])
def update_status():
	try:
		with sql.connect("database.db") as con:
			cur = con.cursor()
            
			cur.execute("UPDATE students set status = 'approved' where company in(select name from company) and pointer>=(select pointer_criteria from company where name=students.company)" )
            
			con.commit()
			msg = "Record successfully updated"
	except:
		con.rollback()
		msg = "error in update operation"
      
	finally:
		return render_template("result.html",msg = msg)
		con.close()	
	
@app.route('/company')
def company_log():
	return render_template('company.html');
	
@app.route('/requestTPO')
def requestTPO():
	return render_template('requestTPO.html');
	
@app.route('/addcompanyrecord',methods=['GET','POST'])
def addcrec():
	if request.method == 'POST':
		try:
			name=request.form['name']
			ptr = request.form['ptr']
         
			with sql.connect("database.db") as con:
				cur = con.cursor()
            
				cur.execute("INSERT INTO company (name,pointer_criteria) VALUES (?,?)",(name,ptr) )
            
				con.commit()
				msg = "Record successfully added"
		except:
			con.rollback()
			msg = "error in insert operation"
      
		finally:
			return render_template("result.html",msg = msg)
			con.close()	

			
@app.route('/shortlist')
def shortlist():
	return render_template("shortlist.html");
	
@app.route('/update-shortlist',methods=['GET','POST'])	
def send_shortlist():
	if request.method == 'POST':
		name=request.form['name']
		id = request.form['id']
		
		try:
			with sql.connect("database.db") as con:
				cur = con.cursor()
            
				cur.execute("UPDATE students set status = 'placed' where id=? and company=?" ,(id,name))
            
				con.commit()
				msg = "Record successfully updated"
		except:
			con.rollback()
			msg = "error in update operation"
      
		finally:
			return render_template("result.html",msg = msg)
			con.close()	

	
if __name__ == '__main__':
   app.run(debug = True)