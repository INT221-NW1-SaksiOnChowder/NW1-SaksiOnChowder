const createAnnouncement = async(announcement) => {
        try {
            const res = await fetch(`http://intproj22.sit.kmutt.ac.th:8080/nw1/api/announcements`,
            // const res = await fetch(`http://localhost:5000/announcements`,
            // const res = await fetch(`http://localhost:8080/api/announcements`,
                {
                    method: 'POST',
                    headers: {
                        'content-type': 'application/json'
                    },
                    body: JSON.stringify(announcement)
                }
            )
            if (res.status === 200) {
                console.log('Create successfully')
            } else {
                throw new Error('Cannot created!')
            }
        } catch (error) {
            console.log(`ERROR cannot create data: ${error}`);
        }
}

export { createAnnouncement }