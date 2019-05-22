import cv2
import numpy as np

cap = cv2.VideoCapture(0)
face_cascade = cv2.CascadeClassifier("haarcascade_frontalface_default.xml")
status = False
counter = 0

while True:
    _, frame = cap.read()

    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    faces = face_cascade.detectMultiScale(gray, 1.3, 5)
    for (x, y, h, w) in faces:
        cv2.rectangle(frame, (x, y), (x+h, y+w), (255, 0, 0), 2)
    cv2.imshow("Faces", frame)

    if status == True:
        frame = frame[y:y+w, x:x+h]
        frame = cv2.resize(frame, (150, 150))
        counter += 1
        cv2.imwrite("/home/calvin/Desktop/facerecognition/image" + str(counter) + ".jpg", frame)
        print("Image ", counter, " successfully saved")

    key = cv2.waitKey(1)
    if key == 32:
        status = not status
    if key == 13 or counter == 10000:
        break

cap.release()
cv2.destroyAllWindows()
