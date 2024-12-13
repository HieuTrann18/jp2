import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;

class Member implements Serializable {
    private String memberID;
    private String memberName;
    private String address;

    public Member() {}

    public Member(String memberID, String memberName, String address) {
        this.memberID = memberID;
        this.memberName = memberName;
        this.address = address;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberID='" + memberID + '\'' +
                ", memberName='" + memberName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

class ClubManager {
    private ArrayList<Member> members = new ArrayList<>();

    public void addMember() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("Enter memberID (e.g., TMB12345): ");
            String memberID = reader.readLine();
            if (!Pattern.matches("[TVA]{1}(MB|MT|MN)\\d{5}", memberID)) {
                System.out.println("Invalid memberID. Please try again.");
                continue;
            }

            System.out.print("Enter memberName: ");
            String memberName = reader.readLine();

            System.out.print("Enter address: ");
            String address = reader.readLine();

            members.add(new Member(memberID, memberName, address));
            System.out.print("Add another member? (yes/no): ");
            if (!reader.readLine().equalsIgnoreCase("yes")) {
                break;
            }
        }
    }

    public void saveToFile(String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(members);
        }
    }

    public void loadFromFile(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            ArrayList<Member> loadedMembers = (ArrayList<Member>) ois.readObject();
            loadedMembers.forEach(System.out::println);
        }
    }

    public static void main(String[] args) {
        try {
            ClubManager manager = new ClubManager();
            manager.addMember();
            manager.saveToFile("member_of_club.txt");
            System.out.println("Members loaded from file:");
            manager.loadFromFile("member_of_club.txt");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

// Question 2: Threads for Day Translation
import java.util.Random;

class DayThread extends Thread {
    private final String[] daysVietnamese = {"Chu nhat", "Thu hai", "Thu ba", "Thu tu", "Thu nam", "Thu sau", "Thu bay"};
    private final String[] daysEnglish = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private String randomDay = "";
    private final Object lock;

    public DayThread(Object lock) {
        this.lock = lock;
    }

    public String getRandomDay() {
        return randomDay;
    }

    @Override
    public void run() {
        synchronized (lock) {
            Random random = new Random();
            int index = random.nextInt(daysVietnamese.length);
            randomDay = daysVietnamese[index];
            System.out.println("Vietnamese: " + randomDay);
            lock.notify();
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class TranslationThread extends Thread {
    private final DayThread dayThread;
    private final Object lock;

    public TranslationThread(DayThread dayThread, Object lock) {
        this.dayThread = dayThread;
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            try {
                lock.wait();
                String vietnameseDay = dayThread.getRandomDay();
                String englishDay = translateToEnglish(vietnameseDay);
                System.out.println("English: " + englishDay);
                lock.notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String translateToEnglish(String vietnameseDay) {
        switch (vietnameseDay) {
            case "Chu nhat": return "Sunday";
            case "Thu hai": return "Monday";
            case "Thu ba": return "Tuesday";
            case "Thu tu": return "Wednesday";
            case "Thu nam": return "Thursday";
            case "Thu sau": return "Friday";
            case "Thu bay": return "Saturday";
            default: return "Unknown";
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Object lock = new Object();
        DayThread dayThread = new DayThread(lock);
        TranslationThread translationThread = new TranslationThread(dayThread, lock);

        dayThread.start();
        translationThread.start();
    }
}