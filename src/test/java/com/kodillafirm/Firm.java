import java.util.*;
public class Firm {
    private String name;
    private String position;
    private double dimensionTime;

    public Firm (String name,String position,double dimensionTime){
        this.name = name;
        this.position=position;
        this.dimensionTime=dimensionTime;

    }

    public String getName() {
        return this.name;
    }
    public String getPosition() { return position;}
    public double getDimensionTime() {return dimensionTime;}

    @Override
    public boolean equals(Object o) {
        Firm e = (Firm)o;
        return this.name.equals(e.getName());

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setDimensionTime(double dimensionTime) {
        this.dimensionTime = dimensionTime;
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(name.substring(0,1));
    }

    @Override
    public String toString() {
        return "Name: "+name+"  Position: "+position+"  Dimension time: "+dimensionTime;
    }
}
interface Employee {
    public double calculateSalary();
}
class OfficeWorkerSalary implements Employee{


    private double baseSalary;
    private double bonus;
    private double dimensionTime;
    public OfficeWorkerSalary(double baseSalary,double bonus,double dimensionTime){
        this.baseSalary = baseSalary;
        this.bonus = bonus;
        this.dimensionTime=dimensionTime;
    }
    public double calculateSalary(){
        return baseSalary*(dimensionTime+bonus);
    }




    @Override
    public String toString() {
        return  "To salary calculation ("+
                baseSalary+","+bonus+","+dimensionTime+")";
    }
}
class WorkerSalary implements Employee{
    private double baseNumberOfHours;
    private double hourlyPay;
    private double overLimitNumberOfHours;

    public WorkerSalary(double baseNumberOfHours,double hourlyPay,double overLimitNumberOfHours){
        this.baseNumberOfHours=baseNumberOfHours;
        this.hourlyPay=hourlyPay;
        this.overLimitNumberOfHours=overLimitNumberOfHours;}

    public double calculateSalary()   {
        return baseNumberOfHours*hourlyPay+overLimitNumberOfHours*1.5*hourlyPay;

    }
    public double getBaseNumberOfHours() {
        return baseNumberOfHours;
    }

    public double getOverLimitNumberOfHours() {
        return overLimitNumberOfHours;
    }

    public double getHourlyPay() {
        return hourlyPay;
    }

    @Override
    public String toString() {
        return  "To salary calculation ("+
                baseNumberOfHours+","+hourlyPay+","+overLimitNumberOfHours+")";
    }
}
class EmployeeSalary extends Firm {
    Employee employee;

    public EmployeeSalary(Employee employee, String name,String position, double dimensionTime) {
        super(name,position,dimensionTime);
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public double showSalary() {
        return this.employee.calculateSalary();
    }

    @Override
    public String toString() {
        return employee+"";//musiałam dodać +"", bo nie kompilowało się bez tego
    }
}

class ShowData {
    public static void main(String[] args) {
        ArrayList<EmployeeSalary> employeeSalariesList = new ArrayList<>();

        Random r = new Random();
        for (int b = 0; b < 50; b++) {
            int numberOfletters = r.nextInt(15) + 5;
            double bonus = Math.random();
            double baseSalary = r.nextInt(10000) + 3000;
            double overLimitHours = r.nextInt(50);
            double baseHours = r.nextInt(174) + 160;
            double hourlyPay = r.nextInt(100) + 20;
            String[] z = {"Office worker", "Worker"};
            int select = r.nextInt(2);
            String position = z[select];
            Double[] y = {0.125, 0.25, 0.375, 0.5, 0.625, 0.75, 0.875, 1.0};
            int select2 = r.nextInt(y.length);
            double dimensionTime = y[select2];

            StringBuilder stringName = new StringBuilder();
            for (int n = 0; n < numberOfletters; n++) {
                char randomLetter = (char) (r.nextInt('z' - 'a') + 'a');
                stringName.append(randomLetter);
            }


            Firm employee1 = new Firm(stringName.toString(), position, dimensionTime);
            Firm employee2 = new Firm(stringName.toString(), position, dimensionTime);

            OfficeWorkerSalary officeWorker1 = new OfficeWorkerSalary(baseSalary, bonus, dimensionTime);
            WorkerSalary worker2 = new WorkerSalary(baseHours, hourlyPay, overLimitHours);


            EmployeeSalary employeeSalary1 = new EmployeeSalary(officeWorker1, stringName.toString(), position, dimensionTime);
            EmployeeSalary employeeSalary2 = new EmployeeSalary(worker2, stringName.toString(), position, dimensionTime);

            employeeSalariesList.add(employeeSalary1);
            employeeSalariesList.add(employeeSalary2);

        }
        WorkerSalary testWorker = new WorkerSalary(160, 30, 10);
        EmployeeSalary testEmployee = new EmployeeSalary(testWorker, "Andy", "Worker", 1.0);
        employeeSalariesList.add(testEmployee);
        System.out.println(employeeSalariesList.size());

        for (EmployeeSalary x : employeeSalariesList) {
            if (x.getName() == "Andy") {
                System.out.println(x.getName() + " " + x.getPosition());
            }
        }

            employeeSalariesList.remove(testEmployee);
            System.out.println(employeeSalariesList.size());

            int numOfWorkers = 0;
        for (EmployeeSalary gp : employeeSalariesList) {
            if(gp.getPosition()=="Worker"){
                 numOfWorkers +=1;}

        }
        int numOfOfficers = employeeSalariesList.size()-numOfWorkers;

            System.out.println("Number of workers is " +numOfWorkers+" and number of Officers is "+numOfOfficers );
            double salarySumWorkers=0;
            double salarySumOfficers=0;
        for (EmployeeSalary gp : employeeSalariesList) {
            if(gp.getPosition()=="Worker"){
            salarySumWorkers +=gp.showSalary();
            }else salarySumOfficers +=gp.showSalary();
        }
            System.out.println("Amount of Workers salaries = "+salarySumWorkers+"Amount of Officers salaries = "+salarySumOfficers);
        }
            }








